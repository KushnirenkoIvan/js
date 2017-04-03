package ua.kushnirenko.entity;

import javax.persistence.*;

@Entity
@Table(name = "Source", schema = "app_db")
public class SourceEntity {
    private long id;
    private String renderingEngine;
    private String browser;
    private String platform;
    private String engineVersion;
    private String cssGrade;

    public SourceEntity() {
    }

    public SourceEntity(long id,
                        String renderingEngine,
                        String browser,
                        String platform,
                        String engineVersion,
                        String cssGrade) {
        this.id = id;
        this.renderingEngine = renderingEngine;
        this.browser = browser;
        this.platform = platform;
        this.engineVersion = engineVersion;
        this.cssGrade = cssGrade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Rendering_engine")
    public String getRenderingEngine() {
        return renderingEngine;
    }

    public void setRenderingEngine(String renderingEngine) {
        this.renderingEngine = renderingEngine;
    }

    @Basic
    @Column(name = "Browser")
    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    @Basic
    @Column(name = "Platform")
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Basic
    @Column(name = "Engine_version")
    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    @Basic
    @Column(name = "Css_grade")
    public String getCssGrade() {
        return cssGrade;
    }

    public void setCssGrade(String cssGrade) {
        this.cssGrade = cssGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SourceEntity that = (SourceEntity) o;

        if (id != that.id) return false;
        if (renderingEngine != null ? !renderingEngine.equals(that.renderingEngine) : that.renderingEngine != null)
            return false;
        if (browser != null ? !browser.equals(that.browser) : that.browser != null) return false;
        if (platform != null ? !platform.equals(that.platform) : that.platform != null) return false;
        if (engineVersion != null ? !engineVersion.equals(that.engineVersion) : that.engineVersion != null)
            return false;
        if (cssGrade != null ? !cssGrade.equals(that.cssGrade) : that.cssGrade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (renderingEngine != null ? renderingEngine.hashCode() : 0);
        result = 31 * result + (browser != null ? browser.hashCode() : 0);
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        result = 31 * result + (engineVersion != null ? engineVersion.hashCode() : 0);
        result = 31 * result + (cssGrade != null ? cssGrade.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SourceEntity{" +
                "id=" + id +
                ", renderingEngine='" + renderingEngine + '\'' +
                ", browser='" + browser + '\'' +
                ", platform='" + platform + '\'' +
                ", engineVersion='" + engineVersion + '\'' +
                ", cssGrade='" + cssGrade + '\'' +
                '}';
    }
}
