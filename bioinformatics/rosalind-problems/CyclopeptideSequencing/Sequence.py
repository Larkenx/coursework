import sys
import filecmp
import itertools

def Sequence(fileName):
    f = open(fileName, "r")
    numberList = f.readlines()[0]
    spectrum = [int(x) for x in numberList.split()]
    f.close()

    acids = list()
    for n in spectrum[1:]:
        if (n > 186):
            break
        else:
            acids.append(n)

    toPrint = ""
    f = open("output.txt", 'w')
    f.truncate()
    acids.sort(reverse=True)
    perms = itertools.permutations(acids, len(acids))

    for perm in perms:
        result = str(perm[0])
        for spec in perm[1:]:
            result += "-" + str(spec)
        f.write(result + " ")
        sys.stdout.write(result + " ")


Sequence("data.txt")
print '\n'
print filecmp.cmp('output.txt', 'proper_output.txt')
