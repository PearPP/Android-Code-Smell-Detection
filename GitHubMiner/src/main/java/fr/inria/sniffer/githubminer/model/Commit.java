package fr.inria.sniffer.githubminer.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sarra on 13/02/17.
 */
public class Commit {

    String sha;
    Developer author;
    Developer committer;
    String message;
    Repository repository;
    Date authoringDate;
    Date commitDate;
    ArrayList<FileModification> fileModifications;



    public Commit(String sha, Developer author, Developer committer, String message, Date authoringDate, Date commitDate, Repository repository) {
        this.sha = sha;
        this.author = author;
        this.committer = committer;
        this.message = message;
        this.authoringDate = authoringDate;
        this.commitDate = commitDate;
        this.repository=repository;
        this.fileModifications=new ArrayList<>();
    }

    public static Commit createCommit(String sha, Developer author, Developer commiter, String message, Date authoringDate, Date commitDate,
                              Repository repository){

        Commit commit=new Commit(sha,author,commiter,message, authoringDate, commitDate, repository);
        repository.addCommit(commit);
        if((commiter !=null) && repository.getContributers().get(commiter.getID()) == null)
        {
            repository.addContributor(commiter);
        }
        if(author !=null && repository.getContributers().get(author.getID()) == null) {
            repository.addContributor(author);
        }

        if(commiter !=null)
        {
            commiter.addCommit(commit);
        }
        if(author != null)
        {
            author.addAuthoredCommit(commit);
        }
        return commit;

    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Developer getAuthor() {
        return author;
    }

    public void setAuthor(Developer author) {
        this.author = author;
    }

    public Developer getCommitter() {
        return committer;
    }

    public void setCommitter(Developer committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Date getAuthoringDate() {
        return authoringDate;
    }

    public void setAuthoringDate(Date authoringDate) {
        this.authoringDate = authoringDate;
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }


    public ArrayList<FileModification> getFileModifications() {
        return fileModifications;
    }

    public void addFileModification(FileModification fileModification) {
        this.fileModifications.add(fileModification);
    }

    public void print(){
        System.out.println("sha: "+this.sha);
        System.out.println("message: "+this.message);
        System.out.println("commit date: "+this.commitDate.toString());
        System.out.println("authoring date: "+this.authoringDate.toString());
        System.out.println("committer: ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        this.committer.print();
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
    }
}
