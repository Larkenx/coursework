Does anyone know if our 1page cheat sheet has to be hand written?
I dont know but he said it could be front and back
^^It can be typed out and printed

Chapter 1
Algorithm for Frequent Words Problem:
Find the most frequent k-mers in a DNA string
Given: DNA string Text and int k
Return: Most frequent k-mers in Text
def patternCnt(s, kmer):
   cnt = 0
   k = len(kmer)
   for i in range(len(s) - k + 1):
       ikmer = s[i:i + k]
       if ikmer == kmer:
           cnt += 1
   return cnt
def freqWords(s, k):
   nmax = 0
   for i in range(len(s) - k + 1):
       kmer = s[i:i + k]
       cnt = patternCnt(s, kmer)
       if nmax < cnt:
           nmax = cnt
           lkmer = [kmer]
       elif nmax == cnt:
           lkmer.append(kmer)
       else:
           pass
   print( ' '.join(stmp for stmp in set(lkmer)))

Algorithm for k neighbors:
def neighbors(pattern, d):
 if d = 0
        	return {pattern}
if len(pattern) = 1
        	return {A,C, G, T}
neighborhood = an empty set
suffixNeighbors = neighbors(Suffix(pattern), d)
for each string text in suffixNeighbors
        	if hammingDistance(suffix(pattern), text) < d
                    	for each nucleotide in {A,C,G,T}
                                	add nucleotide to neighborhood
        	else
                    	add firstSymbol(pattern) + text to neighborhood
return neighborhood

Algorithm for Frequent Words with Mismatches:
This is the version with sorting.
FreqWordsMismatchSort(Text, k, d)
  FreqPatterns = an empty set
  Neighborhoods = []
  for i 0 to |Text| - k
    Neighborhoods.append(Neighbors(Text[i:k], d))
  NeighborhoodArray = Neighborhoods
  for i 0 to |Neighborhoods| - 1:
    pattern = NeighborhoodArray[i]
    index(i) = Pat2#(pattern)
    count(i) = 1
  SortedIndex = sort(index)
  for i 0 to |Neighborhoods| - 1:
    if sortedindex(i) = sortedindex(i + 1)
      count(i + 1) = count(i) + 1
  maxcount = max(count)
  for i 0 to |Text| - k
    if count(i) = maxcount
      Pattern = #2Pat(sortedindex(i), k)
      FreqPatterns.append(pattern)
return Freq Patterns
Algorithm for Clump finding problem:
Find patterns forming clumps in a string.
Given: A string Genome, and Ints k(length of kmer), L(length of window), and t(times kmer is shown).
Return: All distinct k-mers forming (L, t)-clumps in Genome
 For example, TGCA forms a (25, 3)-clump in the following genome:
 gatcagcataagggtccCTGCAATGCATGACAAGCCTGCAGTtgttttac

How to compute Skew diagram:
Skew diagram is defined by plotting Skewi(Genome) where skewi(Genome) is the difference of count G and C up to i
Why can Skew be used for predicting replication origins:
The origin has a characteristic of being located at the minimum of a skew graph due to the way DNA is replicated. DNA is read from 5’ to 3’ but polymerase reads DNA from 3’ to 5’.
Hamming Distance:
Calculate the number of differences between two strings.
Given: Two DNA Strings
Return: Int representing hamming distance
def hammingDistance(s1, s2):
  cnt = 0
  for i in range(len(s1)):
          if s1[i] != s2[i]:
                  cnt += 1
  print(cnt)

Chapter 2
Motif finding problem:
Median string problem:
Given: Collection of strings DNA and an integer k
Output: A K-mer Pattern minimizing d(Pattern, DNA) among all k-mers Pattern
def MedianString(dna, k):
	distance = ∞
	for i in range(0, 4^k - 1):
		pattern = NumberToPattern(i, k)
		distanceBetween = DistanceBetweenPatternAndStrings(pattern, dna)
		if distance > distanceBetween:
			distance = distanceBetween
			median = pattern
	return median

The pseudocode for DistanceBetweenPatternAndStrings function can be found on page 107 of the textbook.
searching algorithm:
Profile:
Frequency of ACGT at each column of a group of DNA
Entropy of profile:
prob*log2(prob) for each columns prob of ACGT
what does Entropy mean?
Entropy is a measure of the uncertainty of the probability distribution
Greedy algorithm and randomized algorithm for motif finding problem:
Gibbs sampling algorithm:

Chapter 3
The genome sequencing problem as the string reconstruction problem:
String Reconstruction Problem:
Reconstruct a string from its k-mer composition
Given: An int k and a collection of Patterns of k-mers
Return: A string Text with a given k-mer composition equal to Patterns

The Hamilton path problem in the overlap graph:
Example overlap graph:

A Hamiltonian Path visits every node in a graph exactly once.  Finding a Hamiltonian Path for an overlap graph provides a solution to the string reconstruction problem.

The de Bruijn graph:
construction from reads:
The Eulerian path problem:
Eulerian path hits every edge once and starts on a different vertex then it ends
The necessary and sufficient condition to tell a directed graph to be Eulerian:
The indegree is equal to the outdegree for all nodes in the graph and the graph is strongly connected(every node in the graph can be reached)
The greedy algorithm for finding an Eulerian cycle in an Eulerian graph:

Paired de Bruijn graph:

Chapter 4
DNA translated into proteins:
Dna must first be read and have RNA (A,U,G,C) made. Groups of 3 RNA bases are read to make 1 amino acid. Chains of amino acids then make proteins.
The theoretical mass spectrum of cyclopeptides and linear peptides:
Cyclopeptides: all substrings of the cyclopeptide (including wrap-around sequences) and the corresponding weights to the substrings, plus the 0 mass and the mass of the entire cyclopeptide
Linear peptides: all substrings of the linear peptide (not including wrap-around sequences) with their corresponding weights, plus mass 0 and the mass of the entire peptide
Integer masses of amino acids
A:  71, C: 103, D: 115, E: 129, F: 147, G:  57, H: 137, I: 113, K: 128, L: 113, M: 131, N: 114, P:  97,
Q: 128, R: 156, S:  87, T: 101, V:  99, W: 186, Y: 163

The algorithm to compute the theoretical mass spectrum:
(p.211-212)

Cyclopeptide sequencing problem:
(p.192)
The branch-and-bound algorithm for cyclopeptide sequencing problem:
(p.196)
CyclopeptideSequencing(Spectrum)
	Peptides ← a set containing only the empty peptide
	While Peptides is nonempty
		Peptides ← Expand(Peptides)
		For each peptide Peptide in Peptides
			If Mass(Peptide) = ParentMass(Spectrum)
				If CycloSpectrum(Peptide) = Spectrum
					Output Peptide
				Remove Peptide from Peptides
			Else if Peptide is not consistent with Spectrum
				Remove Peptide from Peptides
Leaderboard Cyclopeptide sequencing algorithm:
(p.200)
LeaderBoardCyclopeptideSequencing(Spectrum, N)
LeaderBoard ← set containing only the empty peptide
LeaderPeptide ← empty peptide
While LeaderBoard is non-empty
LeaderBoard ← Expand(LeaderBoard)
For each Peptide in LeaderBoard
	If Mass(Peptide) = ParentMss(Spectrum)
		If Score(Peptide, Spectrum) > Score(LeaderPeptide, Spectrum)
			LeaderPeptide ← Peptide
	Else if Mass(Peptide) > ParentMass(Spectrum)
		Remove Peptide from LeaderBoard
LeaderBoard ← Trim(LeaderBoard, Spectrum, N)
Output LeaderPeptide
Restricting amino acid alphabet by spectrum convolution:
Spectrum convolution finds how often mass differences appear. The most frequent masses are usually the masses of single amino acids and they can be assigned to the type of amino acid based on the weight using the amino acid weight table. From this, amino acids that do not appear can be eliminated since they were not in the spectrum.

Chapter 5
Manhattan tourist problem:
Pseudocode (page 210):
ManhattanTourist(n,m, Down, Right)
	S0, 0 ← 0
	for i ← 1 to n
		Si,0 ← Si-1,0 + Downi,0
	for j ← 1 to m
		S0,j ← S0,j-1 + Right0,j
	for i ← 1 to n
		for j ← 1 to m
			Si,j ← max{Si-1,j+Downi,j, Si,j-1 + Righti,j}
	Return Sn,m
The longest path in a directed graph problem:
(p.250)
LongestPath(Graph, source, sink)
	For each node b in Graph
		sb  ← - ∞
	Ssource ← 0
	Topologically order Graph
	For each node b in Graph(following the topological order)
		sb  ← maxall predecessors a of node b {sa + weight of edge from a to b}
	Return ssink
The Dynamic programming algorithm:
The change problem and DP algorithm:
(p.240)
DPChange(money, Coins)
	MinNumCoins(0) ← 0
	For m ← 1 to money
		MinNumCoins(m) ←∞
		For i ← 1 to |Coins|
			If m >= coini
				If MinNumCoins(m-coini) +1 < MinNumCoins(m)
					MinNumCoins(m) ← MinNumCoins(m-coini) +1
	Return MinNumCoins(money)
DP for sequence alignment:
Exercising DP in the Alignment graph:
Scoring matrix for sequence alignment:
(p.254)
Score = #matches - 𝞵 * #mismatches - 𝞼 * #indels
Global alignment and local alignment:
Global alignment tries to get the most matches in the entire strand. Local alignment tries to find the most matches in a certain region of the strand.
Affine gap penalty:
He said in office hours today there would not be a question pertaining to this on the test.
Thanks beaver
Np np. Uhhhh… it has to do with the difference between clumped and separate gaps in alignment or something, but w.e Specifically it has to do with multiple gaps in alignment being worth less of a penalty than a single gap alone.
