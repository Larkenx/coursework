MotifEnumeration(Dna, k, d)
for each k-mer a in Dna
   for each k-mer a’ differing from a by at most d mutations
      if a’ is a (k,d)-mer
         output a’

With the above fn, we can find all the k-mers in a DNA string that differ by at most d mismatches.
Once we have this, we can output a set of all motifs.

Once we have all motifs, we can find a consensus for the most common mismatched letters amongst them
to find an "ideal" DNA string motif.

Example Motifs:

              T  C  G  G  G  G  G  T  T  T  T  T
              C  C  G  G  T  G  A  C  T  T  A  C
              A  C  G  G  G  G  A  T  T  T  T  C
              T  T  G  G  G  G  A  C  T  T  T  T
 Motifs       A  A  G  G  G  G  A  C  T  T  C  C
              T  T  G  G  G  G  A  C  T  T  C  C
              T  C  G  G  G  G  A  T  T  C  A  T
              T  C  G  G  G  G  A  T  T  C  C  T
              T  A  G  G  G  G  A  A  C  T  A  C
              T  C  G  G  G  T  A  T  A  A  C  C

We take the motifs in each column, and total write the nucleotide which appears most
in each column at the bottom, eventually giving us the consensus string. Consensus(Motif)

We also total the number of unpopular nucleotides (anything not the consensus).
This is the Score(Motifs)

A better model is a Profile - which accounts for all the percentages of each nucleotide
for each column. This is so that we can get a more wholesome view of all the possible original
strings.

Our problem is now to find the probability that any one of our profile or consensus strings are actually
the k-mer we want. The way we do this is by multiplying all of the probabilities (profile) of each
nucleotide together.

Prob(6-mer | Profile)
1/8 * 1/8 * 3/8 * 0 * 1/8 * 0

Given motifs, we can construct a profile(motifs).

Given a profile and set of a sequences DNA, we can construct

    Motifs(Profile, Dna)

as a set of Profile-most probable k-mers in each sequence from DNA.

RandomizedMotifSearch(Dna, k, t)
    randomly select k-mers Motifs = (Motif1, … ,Motift) in each  string from DNA
    bestMotifs  ← Motifs
    while forever
       Profile ←  Profile(Motifs)
       Motifs ← Motifs(Profile, Dna)
       if Score(Motifs) < Score(bestMotifs)
          bestMotifs ← Motifs
       else
          return(bestMotifs)
