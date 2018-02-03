import re
from pprint import pprint
import os
a=open("C:/Users/lenovo/AppData/Local/Programs/Python/Python35-32/code/parse.txt","r+")
b=a.read()
stations=dict(re.findall(u'([\u4e00-\u9fa5]+)\|([A-Z]+)',b))

