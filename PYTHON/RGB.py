from PIL import Image
import argparse

ascii_char=list("$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\|()1{}[]?-_+~<>i!lI;:,\"^`\'. ")
#IMG='C:/Users/lenovo/AppData/Local/Programs/Python/Python35-32/code/timg.jpg'
def get_char(r,g,b,alpha=256):
    if(alpha==0):
        return ' '
    length= len(ascii_char)
    gray =int (0.2126*r + 0.7152*g+0.0722*b)
    unit= (256.0 +1)/length
    return ascii_char[int(gray/unit)]
def mop():
    filename='E:/实验用/code/2.jpg'
    img = Image.open(filename)
    imgSize = img.size #图片的长和宽
    Max=int(max(imgSize))#图片的长边
    Min=int(min(imgSize))#图片的短边
    maxSize =int(Max/3) #控制高度
    print('长边：'+str(Max))
    minSize =int(Min/5) #控制宽度
    print('短边：'+str(Min))
    parser =argparse.ArgumentParser()
    parser.add_argument('file',nargs='?',default=filename)
    parser.add_argument('-o','--output')
    parser.add_argument('--width', type = int, default = maxSize) #输出字符画宽
    parser.add_argument('--height', type = int, default =minSize) #输出字符画高
    args = parser.parse_args()
    IMG = args.file
    WIDTH = args.width
    HEIGHT = args.height
    OUTPUT = args.output
    if(__name__=='__main__'):
        im = Image.open(IMG)
        im = im.resize((WIDTH,HEIGHT), Image.NEAREST)

        txt = ""

        for i in range(HEIGHT):
            for j in range(WIDTH):
                txt += get_char(*im.getpixel((j,i)))
            txt += '\n'

        if(OUTPUT):
            with open(OUTPUT,'w') as f:
                f.write(txt)
        else:
            with open('out.txt','w') as f:
                f.write(txt)
mop()
