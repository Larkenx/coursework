from math import log
motif1 = ["TTACCTTAAG", "GTTCCATAGC", "TAAGCTGGAC", "TTACATCAAC", "GTAGATCAAC"]
motif2 = ["ACACAGGCAC", "TGAACCGGAC", "GATCCGGCGC", "CACGGATCTC", "ACACCGGTAC"]
# motif1 = ["GTCG", "GCTG", "GCCT", "CCCG", "GGCG"]

# List of strands => Entropy of motif
def entropy(motif):
    dtmp = {'A': 0, 'C':1, 'G':2, 'T':3}
    profileMatrix = [[0.0 for i in range(4)] for j in range(len(motif[0]))]
    for i in range(len(motif)):
        for j in range(len(motif[i])):
            letter = motif[i][j]
            profileMatrix[j][dtmp[letter]] += 1

    result = 0
    for x in profileMatrix:
        for y in x:
            profile = (float(y) /  float(len(motif)))
            if profile > 0:
                result += profile * log(profile, 2)

    return result * -1

print entropy(motif1)
print entropy(motif2)

    # Now iterate through the matrix, and sum the entropy of each element in each column
    # for i in range (len(motif)):
    #     for j in range(4):
