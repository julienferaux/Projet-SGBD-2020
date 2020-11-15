/*
 1) TABLE ARTICLE
 */
INSERT INTO ARTICLE VALUES ('Adding Structure to Unstructured Data','We develop a new schema for unstructured data. Traditional schemas resemble the
type systems of programming languages.','Long');

INSERT INTO ARTICLE VALUES ('A User-centric Framework for Accessing Biological Sources and Tools','We study the representation and querying of XML with incomplete information.
We consider a simple model for XML data and their DTDs, a very simple query language, and a representation system for incomplete information in the spirit of the representations systems developed by Imielinski and Lipski for relational databases.','Long');
INSERT INTO ARTICLE VALUES ('PDiffView: Viewing the Difference in Provenance of Workflow Results'
,'Provenance Difference Viewer (PDiffView) is a prototype based on these algorithms for differencing runs of SPFL specifications.'
,'Court');
INSERT INTO ARTICLE VALUES ('Automata and Logics for Words and Trees over an Infinite Alphabet','In a data word or a data tree each position carries a label from a finite alphabet and a data value from some infinite domain. These models have been considered in the realm of semistructured data, timed automata and extended temporal logics.
This paper survey several know results on automata and logics manipulating data words and data trees, the focus being on their relative expressive power and decidability.'
,'Long');
INSERT INTO ARTICLE VALUES ('Representing and Querying XML with Incomplete Information','We study the representation and querying of XML with incomplete information. We consider a simple model for XML data and their DTDs, a very simple query language, and a representation system for incomplete information in the spirit of the representations systems developed by Imielinski and Lipski for relational databases. In the scenario we consider, the incomplete information about an XML document is continuously enriched by successive queries to the document.'
,'Long');
INSERT INTO ARTICLE VALUES ('The TLA +  Proof System: Building a Heterogeneous Verification Platform','Model checking has proved to be an efficient technique for finding subtle bugs in concurrent and distributed algorithms and systems. However, it is usually limited to the analysis of small instances of such systems, due to the problem of state space explosion. When model checking finds no more errors, one can attempt to verify the correctness of a model using theorem proving, which also requires efficient tool support.'
,'Long');
INSERT INTO ARTICLE VALUES ('Partial reversal acyclicity','Partial Reversal (PR) is a link reversal algorithm which ensures that an initially directed acyclic graph (DAG) is eventually a destination-oriented DAG. While proofs exist to establish the acyclicity property of PR, they rely on assigning labels to either the nodes or the edges in the graph. In this work we show that such labeling is not necessary and outline a simpler direct proof of the acyclicity property.'
,'Court');
INSERT INTO ARTICLE VALUES ('Reliably Detecting Connectivity Using Local Graph Traits','This paper studies local graph traits and their relationship with global graph properties. Specifically, we focus on graph k-connectivity. First we prove a negative result that shows there does not exist a local graph trait which perfectly captures graph k-connectivity. We then present three different local graph traits which can be used to reliably predict the k-connectivity of a graph with varying degrees of accuracy.'
,'Long');
INSERT INTO ARTICLE VALUES ('Generalized Universality','This paper presents, two decades after k-set consensus was introduced, the generalization with k > 1 of state machine replication. We show that with k-set consensus, any number of processes can emulate k state machines of which at least one remains highly available. While doing so, we also generalize the very notion of consensus universality.'
,'Long');
INSERT INTO ARTICLE VALUES ('Transactional Memory: Glimmer of a Theory','Transactional memory (TM) is a promising paradigm for concurrent programming. This paper is an overview of our recent theoretical work on defining a theory of TM. We first recall some TM correctness properties and then overview results on the inherent power and limitations of TMs.'
,'Tutoriel');

/*
 2) TABLE CHERCHEUR
 */
INSERT INTO CHERCHEUR VALUES ('peter@cis.upenn.edu','Buneman','Peter','http://homepages.inf.ed.ac.uk/opb/');
INSERT INTO CHERCHEUR VALUES ('cohen@lri.fr','Cohen-Boulakia','Sarah','http://www.lri.fr/~cohen');
INSERT INTO CHERCHEUR VALUES ('chris@lri.fr','Froidevaux','Christine','http://www.lri.fr/~chris/');
INSERT INTO CHERCHEUR VALUES ('susan@cis.upenn.edu','Davidson','Susan','http://www.cis.upenn.edu/~susan/');
INSERT INTO CHERCHEUR VALUES ('luc.segoufin@inria.fr','Segoufin','Luc','http://www-rocq.inria.fr/~segoufin/');
INSERT INTO CHERCHEUR VALUES ('lamport@microsoft.com','Lamport','Leslie','http://www.lamport.org/');
INSERT INTO CHERCHEUR VALUES ('lynch@theory.csail.mit.edu','Lynch','Nancy','http://people.csail.mit.edu/lynch/');
INSERT INTO CHERCHEUR VALUES ('Rachid.Guerraoui@epfl.ch','Guerraoui','Rachid','http://lpdwww.epfl.ch/rachid/');

/*
 3) LABORATOIRE
 */
SELECT * FROM LABORATOIRE;

INSERT INTO LABORATOIRE (NOMLABO,SIGLELABO,ADRESSELABO)
VALUES ('Laboratory for Foundations of Computer Science','LFCS','LFCS, School of Informatics Crichton Stree Edinburgh EH8 9LE');
INSERT INTO LABORATOIRE (NOMLABO,SIGLELABO,ADRESSELABO)
VALUES ('Department of Computer and Information Science University of Pennsylvania','CIS','305 Levine/572 Levine North Department of Computer and Information Science  University of Pennsylvania  Levine Hall  3330 Walnut Street  Philadelphia, PA 19104-6389');
INSERT INTO LABORATOIRE (NOMLABO,SIGLELABO,ADRESSELABO)
VALUES ('Laboratoire de Recherche en Informatique','LRI','Bât 490 Université Paris-Sud 11 91405 Orsay Cedex France');
INSERT INTO LABORATOIRE (NOMLABO,SIGLELABO,ADRESSELABO)
VALUES ('Laboratoire Spécification et Vérification','LSV','ENS de Cachan, 61 avenue du Président Wilson, 94235 CACHAN Cedex, FRANCE');

INSERT INTO LABORATOIRE
VALUES ('Distributed Programming Laboratory','LPD','Bat INR 326 Station 14 1015 Lausanne Switzerland','http://lpd.epfl.ch/site/');
INSERT INTO LABORATOIRE
VALUES ('Theory of Distributed Systems','TDS','32 Vassar Street (32-G672A)
Cambridge, MA 02139, USA','http://groups.csail.mit.edu/tds/');
INSERT INTO LABORATOIRE
VALUES ('Microsoft Corporation','Microsoft','1065 La Avenida Mountain View, CA 94043USA.','http://research.microsoft.com');
INSERT INTO LABORATOIRE
VALUES ('INRIA Saclay - Ile-de-France','INRIA Saclay','
Domaine de Voluceau
Rocquencourt - BP 105
78153 Le Chesnay Cedex, France','http://www.inria.fr/centre/saclay');

/*
 4) TABLE SUPPORT
 */
INSERT INTO SUPPORT VALUES ('ICDT','Conference');
INSERT INTO SUPPORT VALUES ('DILS','Conference');
INSERT INTO SUPPORT VALUES ('TODS','Journal');
INSERT INTO SUPPORT VALUES ('VLDB','Journal');
INSERT INTO SUPPORT VALUES ('CLS','Conference');
INSERT INTO SUPPORT VALUES ('CAV','Conference');
INSERT INTO SUPPORT VALUES ('CONCUR','Conference');
INSERT INTO SUPPORT VALUES ('OPODIS','Conference');
INSERT INTO SUPPORT VALUES ('PODC','Conference');
INSERT INTO SUPPORT VALUES ('ICTAC','Conference');

/*
 5) TABLE ANNOTATION
 */
INSERT INTO ANNOTATION VALUES ('data');
INSERT INTO ANNOTATION VALUES ('bio');
INSERT INTO ANNOTATION VALUES ('workflow');
INSERT INTO ANNOTATION VALUES ('theory');
INSERT INTO ANNOTATION VALUES ('XML');
INSERT INTO ANNOTATION VALUES ('Concurrency');
INSERT INTO ANNOTATION VALUES ('TLA');
INSERT INTO ANNOTATION VALUES ('Consencus');
INSERT INTO ANNOTATION VALUES ('Graph');
INSERT INTO ANNOTATION VALUES ('Reliability');

/*
 6) TABLE ECRIRE
 */
SELECT * FROM ECRIRE;

INSERT INTO ECRIRE VALUES ('Adding Structure to Unstructured Data','peter@cis.upenn.edu');
INSERT INTO ECRIRE VALUES ('Adding Structure to Unstructured Data','susan@cis.upenn.edu');
INSERT INTO ECRIRE VALUES ('A User-centric Framework for Accessing Biological Sources and Tools','susan@cis.upenn.edu');
INSERT INTO ECRIRE VALUES ('A User-centric Framework for Accessing Biological Sources and Tools','cohen@lri.fr');
INSERT INTO ECRIRE VALUES ('A User-centric Framework for Accessing Biological Sources and Tools','chris@lri.fr');
INSERT INTO ECRIRE VALUES ('Automata and Logics for Words and Trees over an Infinite Alphabet','luc.segoufin@inria.fr');


INSERT INTO ECRIRE VALUES ('Representing and Querying XML with Incomplete Information','luc.segoufin@inria.fr');
INSERT INTO ECRIRE VALUES ('Generalized Universality','Rachid.Guerraoui@epfl.ch');
INSERT INTO ECRIRE VALUES ('Transactional Memory: Glimmer of a Theory','Rachid.Guerraoui@epfl.ch');
INSERT INTO ECRIRE VALUES ('Reliably Detecting Connectivity Using Local Graph Traits','lynch@theory.csail.mit.edu');
INSERT INTO ECRIRE VALUES ('Partial reversal acyclicity','lynch@theory.csail.mit.edu');
INSERT INTO ECRIRE VALUES ('The TLA +  Proof System: Building a Heterogeneous Verification Platform','lamport@microsoft.com');

/*
 7) TABLE PUBLIER
 */


INSERT INTO PUBLIER VALUES ('Adding Structure to Unstructured Data','ICDT',1997);
INSERT INTO PUBLIER VALUES ('A User-centric Framework for Accessing Biological Sources and Tools','DILS',2005);
INSERT INTO PUBLIER VALUES ('Representing and Querying XML with Incomplete Information','TODS',2006);
INSERT INTO PUBLIER VALUES ('PDiffView: Viewing the Difference in Provenance of Workflow Results','VLDB',2009);
INSERT INTO PUBLIER VALUES ('Automata and Logics for Words and Trees over an Infinite Alphabet','CLS',2006);
INSERT INTO PUBLIER VALUES ('The TLA +  Proof System: Building a Heterogeneous Verification Platform','ICTAC',2009);
INSERT INTO PUBLIER VALUES ('Partial reversal acyclicity','PODC',2011);
INSERT INTO PUBLIER VALUES ('Reliably Detecting Connectivity Using Local Graph Traits','OPODIS',2010);
INSERT INTO PUBLIER VALUES ('Generalized Universality','CONCUR',2011);
INSERT INTO PUBLIER VALUES ('Transactional Memory: Glimmer of a Theory','CAV',2010);

/*
 8)
 */
INSERT INTO TRAVAILLER VALUES ('peter@cis.upenn.edu','Laboratory for Foundations of Computer Science');
INSERT INTO TRAVAILLER VALUES ('susan@cis.upenn.edu','Department of Computer and Information Science University of Pennsylvania');
INSERT INTO TRAVAILLER VALUES ('peter@cis.upenn.edu','Department of Computer and Information Science University of Pennsylvania');
INSERT INTO TRAVAILLER VALUES ('cohen@lri.fr','Laboratoire de Recherche en Informatique');
INSERT INTO TRAVAILLER VALUES ('chris@lri.fr','Laboratoire de Recherche en Informatique');
INSERT INTO TRAVAILLER VALUES ('luc.segoufin@inria.fr','Laboratoire Spécification et Vérification');
INSERT INTO TRAVAILLER VALUES ('luc.segoufin@inria.fr','INRIA Saclay - Ile-de-France');
INSERT INTO TRAVAILLER VALUES ('lamport@microsoft.com','Microsoft Corporation');
INSERT INTO TRAVAILLER VALUES ('lynch@theory.csail.mit.edu','Theory of Distributed Systems');
INSERT INTO TRAVAILLER VALUES ('Rachid.Guerraoui@epfl.ch','Distributed Programming Laboratory');
INSERT INTO TRAVAILLER VALUES ('Rachid.Guerraoui@epfl.ch','INRIA Saclay - Ile-de-France');

/*
 9) TABLE ANNOTER
 */

INSERT INTO ANNOTER VALUES ('luc.segoufin@inria.fr','Adding Structure to Unstructured Data','data');
INSERT INTO ANNOTER VALUES ('peter@cis.upenn.edu','A User-centric Framework for Accessing Biological Sources and Tools','bio');
INSERT INTO ANNOTER VALUES ('peter@cis.upenn.edu','Adding Structure to Unstructured Data','XML');
INSERT INTO ANNOTER VALUES ('peter@cis.upenn.edu','PDiffView: Viewing the Difference in Provenance of Workflow Results','workflow');
INSERT INTO ANNOTER VALUES ('cohen@lri.fr','Automata and Logics for Words and Trees over an Infinite Alphabet','theory');
INSERT INTO ANNOTER VALUES ('lamport@microsoft.com','The TLA +  Proof System: Building a Heterogeneous Verification Platform','TLA');
INSERT INTO ANNOTER VALUES ('lynch@theory.csail.mit.edu','Generalized Universality','Consencus');
INSERT INTO ANNOTER VALUES ('lynch@theory.csail.mit.edu','Transactional Memory: Glimmer of a Theory','Concurrency');
INSERT INTO ANNOTER VALUES ('Rachid.Guerraoui@epfl.ch','Partial reversal acyclicity','Graph');
INSERT INTO ANNOTER VALUES ('Rachid.Guerraoui@epfl.ch','Reliably Detecting Connectivity Using Local Graph Traits','Reliability');

/*
 10) TABLE NOTER
 */

INSERT INTO NOTER VALUES ('luc.segoufin@inria.fr','Adding Structure to Unstructured Data',4);
INSERT INTO NOTER VALUES ('luc.segoufin@inria.fr','Automata and Logics for Words and Trees over an Infinite Alphabet',1);
INSERT INTO NOTER VALUES ('luc.segoufin@inria.fr','A User-centric Framework for Accessing Biological Sources and Tools',4);
INSERT INTO NOTER VALUES ('luc.segoufin@inria.fr','PDiffView: Viewing the Difference in Provenance of Workflow Results',5);
INSERT INTO NOTER VALUES ('luc.segoufin@inria.fr','Representing and Querying XML with Incomplete Information',1);
INSERT INTO NOTER VALUES ('peter@cis.upenn.edu','A User-centric Framework for Accessing Biological Sources and Tools',2);
INSERT INTO NOTER VALUES ('peter@cis.upenn.edu','Automata and Logics for Words and Trees over an Infinite Alphabet',1);
INSERT INTO NOTER VALUES ('cohen@lri.fr','A User-centric Framework for Accessing Biological Sources and Tools',2);
INSERT INTO NOTER VALUES ('cohen@lri.fr','PDiffView: Viewing the Difference in Provenance of Workflow Results',1);
INSERT INTO NOTER VALUES ('Rachid.Guerraoui@epfl.ch','Adding Structure to Unstructured Data',1);
INSERT INTO NOTER VALUES ('Rachid.Guerraoui@epfl.ch','Automata and Logics for Words and Trees over an Infinite Alphabet',4);
INSERT INTO NOTER VALUES ('Rachid.Guerraoui@epfl.ch','A User-centric Framework for Accessing Biological Sources and Tools',2);
INSERT INTO NOTER VALUES ('Rachid.Guerraoui@epfl.ch','PDiffView: Viewing the Difference in Provenance of Workflow Results',1);
INSERT INTO NOTER VALUES ('Rachid.Guerraoui@epfl.ch','Representing and Querying XML with Incomplete Information',5);
INSERT INTO NOTER VALUES ('lamport@microsoft.com','A User-centric Framework for Accessing Biological Sources and Tools',3);
INSERT INTO NOTER VALUES ('lamport@microsoft.com','Automata and Logics for Words and Trees over an Infinite Alphabet',4);

commit ;







