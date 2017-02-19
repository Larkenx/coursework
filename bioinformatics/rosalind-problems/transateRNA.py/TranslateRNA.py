from Bio.Seq import Seq
from Bio.Alphabet import generic_rna

f = open("data.txt", "r")
content = [x.strip('\n') for x in f.readlines()]
rna = Seq(content[0], generic_rna)
print rna.translate()
