import os
import signal
import subprocess


class ShellUtility:
    def run_shell_cmd(self, cmd, timeout=10):
        if timeout > 0:
            process = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE,
                                       shell=True, close_fds=True, start_new_session=True, executable="/bin/bash")
            try:
                stdout, stderr = process.communicate(timeout=timeout)
                return_code = process.poll()
                log = f"exec shell cmd: {cmd}\nreturn code:{process.returncode}\nstdout:\n{stdout.decode('utf-8')}\nstderr:\n{stderr.decode('utf-8')}"
                print(log)
            except subprocess.TimeoutExpired:
                try:
                    print("time out, terminating process ...")
                    process.kill()
                    process.terminate()
                    os.killpg(process.pid, signal.SIGTERM)
                except Exception as ex:
                    print(f"fail to terminate process {ex}")
                return_code, stdout, stderr = -15, b"", b"timeout exception"
            except Exception as ex:
                print(f"meet unknown exception: {ex}")
                return_code, stdout, stderr = -1, b"", b"unknown exception"
            if return_code != 0:
                raise Exception(log)
        else:
            process = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE,
                                       shell=True, executable="/bin/bash")
            stdout, stderr = process.communicate()
            return_code = process.returncode
            log = f"exec shell cmd: {cmd}\nreturn code:{return_code}\nstdout:\n{stdout.decode('utf-8')}\nstderr:\n{stderr.decode('utf-8')}"
            print(log)
            if return_code != 0:
                raise Exception(log)

        return return_code, str(stdout.decode('utf-8')), str(stderr.decode('utf-8'))
