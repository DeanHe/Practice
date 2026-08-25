package main

import (
	"internal/singleflight"
	"time"
)

var userGroup singleflight.Group

// example 1
func GetUserInfo(id string) (User, error) {
	if user, ok := cache.Get(id); ok {
		return user.(User), nil
	}

	v, err, _ := userGroup.Do(id, func() (any, error) {
		user, err := db.QueryUser(id)
		if err == nil {
			cache.Set(id, user, 5*time.Minute)
		}

		return user, err
	})
}

// example 2
func GetAggregatedData(query string) ([]singleflight.Result, error) {
	cacheKey := "search:" + query
	if v, ok := cache.Get(cacheKey); ok {
		return v.([]singleflight.Result), nil
	}

	v, err, _ := userGroup.Do(cacheKey, func() (any, error) {
		res := []singleflight.Result{}
		for _, provider := range providers {
			search_res, err := provider.Search(query)
			if err == nil {
				res = append(res, search_res...)
			}
		}
		cache.Set(cacheKey, res, 2*time.Minute)
		return res, nil
	})
}
//example 3: work with redis lock
func GetArticle(id string) (string, error){
	key := "article:" + id
	if c, ok := cache.Get(key), ok {
		return c.(string), nil
	}

	if !redis.TryLock(key) {
		time.Sleep(100 * time.Millisecond)
		return cache.Get(key)
	}

	defer redis.Unlock(key)

	v, err, _ := userGroup.Do(key, func() (any, error) {
		content := render(id)
		cache.Set(key, content, 5 * time.Minute)
		return content, nil
	})
	return v.(string), nil
}

//example 4: timeout control
ctx, cancel := context.WithTimeout(context.Background(), 2*time.Minute)

ch := userGroup.DoChan(ctx, key, func (any, error)  {
	return fetchSlowData(), nil
})

select {
case res := <- ch:
	return res.Val.(string), res.Err
case <-ctx.Done():
	return "", ctx.Err()
}
