def cycleToChromosome(Nodes):
    Chromosome = list()
    for j in range(len(Nodes) / 2):
        if (Nodes[2*j - 1] < Nodes[2*j]):
            Chromosome.append(Node[2*j / 2])
        else:
            Chromosome.append(-1 * Node[(2*j - 1) / 2])
    return Chromosome

def GraphToGenome(GenomeGraph):
    Genome = list()
    for





     P ← an empty set of chromosomes
     for each cycle Nodes in GenomeGraph
          Chromosome ← CycleToChromosome(Nodes)
          add Chromosome to P
     return P
