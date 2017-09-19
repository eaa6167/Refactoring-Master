package org.refactoringminer;

import java.util.List;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.refactoringminer.api.GitHistoryRefactoringMiner;
import org.refactoringminer.api.GitService;
import org.refactoringminer.api.Refactoring;
import org.refactoringminer.api.RefactoringHandler;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;

public class RefactoringMiner {

    public static void main(String[] args) throws Exception {
        /*if (args.length != 2) {
            throw new IllegalArgumentException("Usage: RefactoringMiner <git-repo-folder> <commit-SHA1>");
        }*/
        final String folder = "C:/Users/Eman/Desktop/refactoring-toy-example-master";

   
        final String commitId = "05c1e773878bbacae64112f70964f4f2f7944398";
        GitService gitService = new GitServiceImpl(); 
        try (Repository repo = gitService.cloneIfNotExists(
        	    "tmp/refactoring-toy-example",
        	    "https://github.com/danilofes/refactoring-toy-example.git");

) {
            GitHistoryRefactoringMiner detector = new GitHistoryRefactoringMinerImpl();
            detector.detectAll(repo, "master", new RefactoringHandler() {
                @Override
                public void handle(RevCommit commitData, List<Refactoring> refactorings) {
                	 if (refactorings.isEmpty()) {
                         System.out.println("No refactorings found in commit " + commitId);
                     } else {
                         System.out.println(refactorings.size() + " refactorings found in commit " + commitId + ": ");
                         for (Refactoring ref : refactorings) {
                             System.out.println("  " + ref);
                         }
                     }
                }
  /*          detector.detectAtCommit(repo, "https://github.com/danilofes/refactoring-toy-example.git", "05c1e773878bbacae64112f70964f4f2f7944398", new RefactoringHandler() {
                @Override
                public void handle(String commitId, List<Refactoring> refactorings) {
                    if (refactorings.isEmpty()) {
                        System.out.println("No refactorings found in commit " + commitId);
                    } else {
                        System.out.println(refactorings.size() + " refactorings found in commit " + commitId + ": ");
                        for (Refactoring ref : refactorings) {
                            System.out.println("  " + ref);
                        }
                    }
                }
*/

           
            
                @Override
                public void handleException(String commit, Exception e) {
                    System.err.println("Error processing commit " + commitId);
                    e.printStackTrace(System.err);
                }
            });
        }
    }

}
