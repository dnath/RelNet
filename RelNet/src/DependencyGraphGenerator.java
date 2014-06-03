import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.ParserAnnotator;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.BasicDependenciesAnnotation;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedDependenciesAnnotation;
import edu.stanford.nlp.trees.Dependencies;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class DependencyGraphGenerator {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("annotators", "tokenize,ssplit,pos,lemma,parse");
		
		StanfordCoreNLP snlp = new StanfordCoreNLP(props);
		// System.out.println(snlp);
		
		String document = "The quick brown fox jumped over the lazy dog, Milo. " 
							+ "Then he ran away. He lived and hunted in the forest";
		System.out.println("document: " + document);
		
		Annotation annotatedDoc = new Annotation(document);
		System.out.println("annotatedDoc: " + annotatedDoc);
		
		snlp.annotate(annotatedDoc);
		
		System.out.println("\nSentencesAnnotation");
		List<CoreMap> sentences = annotatedDoc.get(SentencesAnnotation.class);
		
		System.out.println("sentences.size() = " + sentences.size());
		
		for (CoreMap sentence : sentences) {
			System.out.println("sentence = " + sentence);
			
			System.out.println("TreeAnnotation =");
			Tree tree = sentence.get(TreeAnnotation.class);
			tree.pennPrint();
			
			System.out.println("BasicDependenciesAnnotation =");
			SemanticGraph smGraph = sentence.get(BasicDependenciesAnnotation.class);
			System.out.println(smGraph.toString("plain"));
			
			System.out.println("CollapsedDependenciesAnnotation =");
			smGraph = sentence.get(CollapsedDependenciesAnnotation.class);
			System.out.println(smGraph.toString("plain"));
			
			System.out.println("CollapsedCCProcessedDependenciesAnnotation =");
			smGraph = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
			System.out.println(smGraph.toString("plain"));
		}

	}

}
