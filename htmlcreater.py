import os
current_file = "testfile.html"

f = open(current_file, "r")
print(f.read())

change = input("do you want to change the contents of the current file?: Yes, No")
if change == "Yes":
    f.close()
    content = input("new content:")
    end_content = content.split()
    print(end_content)
    os.remove(current_file)
    h = open(current_file, "a")
    h.write("<!DOCTYPE html>" + '\n' + "<head> hello world </head>" + '\n' + "<body>" + '\n' + "    <p>test test</p>" + '\n' + "</body>" + '\n' + "<!--the reading on this file starts at the beggining of the last line and ends on it's end, every comma will be treated as a new line on the out programm reading-->" + '\n')
    for i in range(len(end_content)):
        h.write(end_content[i])
        h.write(" , ")
    h.close()

