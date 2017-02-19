HT = [None] * 10
values = [3, 12, 9, 2, 79, 44]


def h(k):
    return k % 10

def p(i):
    return [5, 9, 2, 1, 4, 8, 6, 3, 7][i]

def hashInsert(k):
    home = h(k)
    currPos = h(k)
    i = 0
    while (HT[currPos] != None):
        currPos = (home + p(k)) % 10

    HT[currPos] = k

for x in values:
    hashInsert(x)

print HT




# void hashInsert(Key k, E r) {
#     int home; // Home position for r
#     int pos = home = h(k); // Initial position
#     for (int i=1; HT[pos] != null; i++) {
#         pos = (home + p(k, i)) % M; // Next pobe slot
#         assert HT[pos].key().compareTo(k) != 0 : "Duplicates not allowed";
#     }
#     HT[pos] = new KVpair<Key,E>(k, r); // Insert R
# }
