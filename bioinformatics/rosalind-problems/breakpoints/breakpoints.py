import re

perm = [int(x) for x in re.sub('[()+]', '', "(+3 +4 +5 -12 -8 -7 -6 +1 +2 +10 +9 -11 +13 +14)").split(' ')]
perm.insert(0, 0)
perm.append(len(perm))

breakpoints = 0
for i in range(len(perm) - 1):
    if (perm[i + 1] - perm[i] != 1):
        breakpoints += 1

print breakpoints
