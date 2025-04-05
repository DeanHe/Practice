package daemon

import (
	"net"
	"os"
	"os/exec"
)

func StartDaemon() error {
	pwd, err := os.Getwd()
	if err != nil {
		return err
	}

	args := []string{"-daemon"}
	args = append(args, os.Args[1:]...)

	cmd := exec.Command(os.Args[0], args...)
	cmd.Dir = pwd
	cmd.Env = os.Environ()

	return cmd.Start()
}

func RestartGracefully() error {
	listener, err := net.Listen("tcp", ":8080")
	if err != nil {
		return err
	}

	//fork child process
	cmd := exec.Command(os.Args[0], os.Args[1:]...)
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr
	file, _ := listener.(*net.TCPListener).File()
	cmd.ExtraFiles = []*os.File{file}

	err = cmd.Start()
	if err != nil {
		return err
	}

	//wait for connections

	return nil
}
