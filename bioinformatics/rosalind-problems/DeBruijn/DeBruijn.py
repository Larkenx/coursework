import filecmp

def printHelper(arr):
    result = ""
    for x in arr:
        result += x
        result += ","
    return result[:-1] # strip the last comma and whitespace

def DeBruijn(fileName):
    f = open(fileName, "r")
    result = dict()
    patterns = [x.strip('\n') for x in f.readlines()]
    f.close()
    patterns.sort()
    for pattern in patterns:
        current = pattern[:-1]
        if (not current in result):
            result[current] = list()
        result[current].append(pattern[1:])

    # Print our result
    f = open("output.txt", 'w')
    f.truncate()
    for k,v in sorted(result.items()):
        toWrite = k + " -> " + printHelper(v) + '\n'
        f.write(toWrite)

DeBruijn("data.txt")
