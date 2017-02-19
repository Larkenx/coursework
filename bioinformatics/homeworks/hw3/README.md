# Homework 3
## Steven Myers
***Please view my homework in a markdown viewer. This assignment is uploaded to my github, at this location: https://github.iu.edu/B363-2016/stelmyer/blob/master/homeworks/hw3***

**1)** - The DNA Sequence from this Eulerian path is: AGCAGCTGCTGCA.

    AGCAGCTGCTGCA
    AG AG
     GC GC
      CA CT
       AG TG
        GC GC
         CT CT
          TG TG
           GC GC
            CT CA


**2)** - His rationale is that nodes with the highest indegree have more Eulerian paths such that they result in Eulerian cycles. The number of Eulerian cycles for a node with N indegree is N cycles. It is not always true that the first cycle you get to is a Eulerian cycle. In figure 3.22 on page 144 of the textbook, if we were to start with the node with the highest indegree, we can easily follow a path that would result in a path in which we have not yet visited every edge.

**3)** Unfinished

**4)** Mr Fuzzy's algorithm is correct. Mr Fuzzy's algorithm has a true runtime of roughly `(n*2(n - index(m1))) + 2(nlogn) + 2m` if he uses quicksort in the best case. This puts his algorithm in the `O(n^2)` time complexity. The first for loop is closer to `n*n!`, depending on how many duplicate amino acids there are.

One way to optimize Mr Fuzzy's algorithm and eliminate the algorithm's dependence on the size of the MaxMass would be to make Count a set instead of an array. You could then initialize the size of the Count set to be `2^(n) / 2 where n is the length of the peptide`, since there are that many possible differences. This is equivalent to the length of the power-set of the peptide divided by two.

*Fuzzy's Original Algorithm*

    MaxMass <- maximum integer in Spectrum
    Initialize an array Count of size MaxMass with all values = 0
    Sort Spectrum in increasing order // nlogn
    for each M1 in Spectrum // N
        for each M2 > M1 in Spectrum // N - Spectrum.LastIndexOf(M1)
            Diff <- M2 – M1 // 1
            Count[Diff] <- Count[Diff] + 1 // 1
        ConvolutionSpectrum empty set // 1
    for i<-1 to MaxMass // M
        if Count[Diff] > 0 // 1
            add (Diff, Count[Diff]) into ConvolutionSpectrum // (mass, multiplicity) // 1
    Sort ConvolutionSpectrum in decreasing order of the multiplicity // nlogn
    Output ConvolutionSpectrum

**5)** -
Mr Fuzzy is incorrect in stating that a theoretical spectrum of a cyclopeptide is a superset of a linear peptide constructed by separating the cyclopeptide at any point, because the theoretical spectrum for both the linear and cyclopeptides are the same. The theoretical spectrum is just the mass values of the power-set of all the amino acids in a peptide so order doesn't matter. Rebuilding a linear peptide is indeed the same as the cyclopeptide, since both peptides share a permutation or proper order, but he is incorrect in assuming that the linear peptide is a subset of the cyclopeptide.

Here is a counterexample showing the powersets are the same:

    Cyclopeptide: NQ
    Powerset: "", N, Q, NQ
    Linear Peptide: QN
    Powerset: "", Q, N, QN

**6)**


    # Counting Peptides with Given Mass Problem:
    # Compute the number of peptides with the given mass.
    Input: An integer m.
    Output: The number of linear peptides having integer mass m.

    # using this class to store results
    class Peptide:
        def __init__(self, current, prev, mass):
            self.mass
            self.current = current
            self.prev = prev

    CountPeptides(mass):
        Count <- 0 # number of linear peptides
        AcidsTable <- Map of pairs (Amino Acid Mass, Amino Acid String)
        ResultsTable <- empty set # stores acid combinations we've seen so far
        for (acid in AcidsTable):
            currentResult = new Peptide(acid.getString(), null, acid.getMass())
            while (currentResult.mass < mass):
                for (acid in AcidsTable):
                    if (currentResult in ResultsTable):
                    currentResult = new Peptide(acid.getString(), ResultsTable[currentResult], currentResult.mass() + ResultsTable[currentResult].mass)
                    else:
                        newMass = acid.getMass() + currentResult.mass
                        currentResult = new Peptide(acid.getString(), currentResult, newMass)
                        ResultsTable <- currentResult
            if (currentResult.mass == mass):
                Count++
        output Count


**7)** - In order to determine false noisy parent mass spectrums for a cyclopeptide, I will re-use Mr Fuzzy's ConvolutionSpectrum algorithm.

Assuming we are given the noisy mass spectrum, we can do the following to refute false parents.

    Input: noisy mass spectrum.
    EliminateNoisyParent(Spectrum):
    PossibleParentMass = null;
    while (PossibleParentMass is null):
        PossibleParentMass <- Max Spectrum in Spectrum
        # Eventually, we will reach a combination of spectrums
        # whose sum will total to the mass of the parent. If no
        # such sum exists, then the parent is invalid.
        for each M1 in Spectrum:
            for each M2 != M1 in Spectrum:
                if M2 + M1 == PossibleParentMass # It matches!
                    output PossibleParentMass;
        # If we reach here, it shows that our other spectrums
        # do not validate the mass value as being the sum of
        # the subpeptides. We could theoretically add in a margin of
        # error, like if the sum comes within a certain amount, then
        # we keep the value
        Spectrum.remove(PossibleParentMass)
        PossibleParentMass = null;

**8)** `→` represents a deletion in the first string, `↓` gap in second string, and `↘︎` represents a match. Total score is 11, since we have two deletions `-2 + -2 = -4` and five matches `5*3` for a total score of `-4 + 15 = 11`.

![Image didn't load properly. Look for graph.png in the repo.]
(graph.png)

**9)** Unfinished

**10)** - Inbox message from Professor Tang said not to do this problem!
