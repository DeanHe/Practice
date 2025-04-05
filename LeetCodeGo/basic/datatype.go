package main

import (
	"bytes"
	"encoding/binary"
	"errors"
	"reflect"
)

func serialize_by_type(arg any) ([]byte, error) {
	switch v := arg.(type) {
	case int:
		buffer := bytes.NewBuffer(make([]byte, 0, 8))
		err := binary.Write(buffer, binary.BigEndian, int64(v))
		if err != nil {
			return nil, err
		} else {
			return buffer.Bytes(), nil
		}
	case string:
		return []byte(v), nil
	default:
		return nil, errors.New("type not support")
	}
}

func serialize_by_reflect(arg any) ([]byte, error) {
	v := reflect.ValueOf(arg)
	switch v.Kind() {
	case reflect.Int:
		buffer := bytes.NewBuffer(make([]byte, 0, 8))
		err := binary.Write(buffer, binary.BigEndian, int64(v.Interface().(int)))
		if err != nil {
			return nil, err
		} else {
			return buffer.Bytes(), nil
		}
	case reflect.String:
		return []byte(v.Interface().(string)), nil
	default:
		return nil, errors.New("type not support")
	}
}

type GenericType interface {
	int | string
}

func serialize[T GenericType](arg T) ([]byte, error) {
	return serialize_by_type(arg)
	// return serialize_by_reflect(arg)
}

func main() {
	i := 8
	s := "str"
	serialize_by_type(i)
	serialize_by_reflect(s)
}
