package uteq.student.project.examenfinal.models;

public class Issues {

    private String issue_id;
    private String volume;
    private String number;
    private String year;
    private String date_published;
    private String title;
    private String doi;
    private String cover;

    public Issues() {
    }

    public Issues(String issue_id) {
        this.issue_id = issue_id;
    }

    public Issues(String issue_id, String volume, String number, String year, String date_published, String title, String doi, String cover) {
        this.issue_id = issue_id;
        this.volume = volume;
        this.number = number;
        this.year = year;
        this.date_published = date_published;
        this.title = title;
        this.doi = doi;
        this.cover = cover;
    }
}
