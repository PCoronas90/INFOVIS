package org.infovis.finalproject.indexer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.infovis.finalproject.utils.ParsingRfc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class IndexerRFC {

	private IndexerRFC() {
	}

	public static void main(String[] args) {

		String indexPath = "indexRFC";
		String DocsPath ="C:/Users/Pietro/Desktop/rfc-index.txt";
		boolean create = true;

		final File DocDir = new File(DocsPath);
		if (!DocDir.exists() || !DocDir.canRead()) {
			System.out.println("Document directory '" + DocDir.getAbsolutePath()
					+ "' does not exist or is not readable, please check the path");
			System.exit(1);
		}

		Date start = new Date();

		try {
			System.out.println("Indexing to directory '" + indexPath + "'...");

			Directory indexDir = FSDirectory.open(new File(indexPath));
			// :Post-Release-Update-Version.LUCENE_XY:
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
			IndexWriterConfig indexWriterConf = new IndexWriterConfig(Version.LUCENE_47, analyzer);

			if (create) {
				// Creo nuovo indice e rimuovo i precedenti
				indexWriterConf.setOpenMode(OpenMode.CREATE);
			} else {
				// Aggiungo il documento ad un indice esistente
				indexWriterConf.setOpenMode(OpenMode.CREATE_OR_APPEND);
			}

			IndexWriter writer = new IndexWriter(indexDir, indexWriterConf);
			indexDocument(writer, DocDir);

			writer.close();
			

			Date end = new Date();
			System.out.println(end.getTime() - start.getTime() + " total milliseconds");

		} catch (IOException e) {
			System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
		}
	}

	static void indexDocument(IndexWriter writer, File file) throws IOException {
		// do not try to index files that cannot be read
		if (file.canRead()) {
			if (file.isDirectory()) {
				String[] files = file.list();
				// an IO error could occur
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						indexDocument(writer, new File(file, files[i]));
					}
				}
			}

			else {

				try {

					Map<String, ArrayList<String>> infoWithField = ParsingRfc.parse(file);
					
					// make a new, empty document
					
                    
					Set<String> rfcs= infoWithField.keySet();
					for(String rfc :rfcs){
					Document doc = new Document();
					Field title= new TextField("rfc", rfc, Field.Store.YES);
					doc.add(title);	
					
					
					
					
					

					FieldType type = new FieldType();
					type.setIndexed(true);
					type.setStored(true);
					type.setStoreTermVectors(true);
					type.setTokenized(true);
					type.setStoreTermVectorOffsets(true);
					

					if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
						System.out.println("adding " + file);
						writer.addDocument(doc);
					}

					else {
						System.out.println("updating " + file);
						writer.updateDocument(new Term("path", file.getPath()), doc);
					}}

				} finally {
				}
			}
		}
	}}


