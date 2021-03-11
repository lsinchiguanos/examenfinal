package uteq.student.project.examenfinal.models;

public class Pubs {

    private String publication_id;
    private String section;
    private String title;
    private String doi;
    private String pabstract;
    private String date_published;
    private String submission_id;
    private String section_id;
    private String seq;

    public Pubs() {
    }

    public Pubs(String publication_id, String section, String title, String doi, String pabstract, String date_published, String submission_id, String section_id, String seq) {
        this.publication_id = publication_id;
        this.section = section;
        this.title = title;
        this.doi = doi;
        this.pabstract = pabstract;
        this.date_published = date_published;
        this.submission_id = submission_id;
        this.section_id = section_id;
        this.seq = seq;
    }

    public String getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(String publication_id) {
        this.publication_id = publication_id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getPabstract() {
        return pabstract;
    }

    public void setPabstract(String pabstract) {
        this.pabstract = pabstract;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getSubmission_id() {
        return submission_id;
    }

    public void setSubmission_id(String submission_id) {
        this.submission_id = submission_id;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
}
