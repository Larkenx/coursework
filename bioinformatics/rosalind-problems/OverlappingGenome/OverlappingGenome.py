f = open("data.txt", "r")
content = [x.strip('\n') for x in f.readlines()]
result = content[0]
for i in range(1, len(content)):
    result += content[i][-1:]
print result
