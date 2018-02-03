def GetMiddleStr(content,startStr,endStr):#定义一个函数，函数作用为选取content字符串中以startStr开始，以endStr结尾的字符串
    startIndex = content.index(startStr)#获取startStr在content中的首字符位置，index是获取第一次出现的位置，rindex是获取最后一次出现的位置
    if startIndex>=0:#获取startStr在content中的尾字符位置
        startIndex += len(startStr)
        content=content[startIndex-1:]
    endIndex = content.index(endStr)#获取endStr在content中的首字符位置
    return content[1:endIndex]#返回修改后的content

x='asduoqwqenq,neqkhuibadnkjasndqkea'
print(GetMiddleStr(x,'a','e'))
