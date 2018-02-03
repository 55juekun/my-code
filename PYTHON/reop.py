import os
import time
import sys
def re_pro():
    python = sys.executable
    os.execl(python, python, * sys.argv)
time.sleep(1)
print("asd")
re_pro()
