package basic

type User struct {
	Name string
	Age  int
}

type UserOption func(*User)

func WithName(name string) UserOption {
	return func(user *User) {
		user.Name = name
	}
}

func WithAge(age int) UserOption {
	return func(user *User) {
		user.Age = age
	}
}

func NewUser(options ...UserOption) *User {
	user := new(User)
	for _, option := range options {
		option(user)
	}
	return user
}

type QueryOption interface {
	Apply([]*User) []*User
}

type Where struct {
	Name    string
	FromAge int
	ToAge   int
}

func (w Where) Apply(users []*User) []*User {
	res := make([]*User, 0, len(users))
	for _, user := range users {
		if user.Name == w.Name && user.Age >= w.FromAge && user.Age <= w.ToAge {
			res = append(res, user)
		}
	}
	return res
}

type Limit struct {
	Offset int
	Count  int
}

func (l Limit) Apply(users []*User) []*User {
	if l.Offset >= len(users) {
		return nil
	}
	if l.Offset+l.Count >= len(users) {
		return users[l.Offset:]
	}
	return users[l.Offset : l.Offset+l.Count]
}

func QueryUser(users []*User, options ...QueryOption) []*User {
	res := users
	for _, option := range options {
		res = option.Apply(res)
	}
	return res
}

func main() {
	user := NewUser(WithName("Alex"), WithAge(12))
	print(user)

	users := []*User{user}
	QueryUser(users, Where{Name: "Luke"}, Limit{Offset: 0, Count: 2})
}
