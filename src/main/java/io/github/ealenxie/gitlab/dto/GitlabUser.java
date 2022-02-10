package io.github.ealenxie.gitlab.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by EalenXie on 2022/2/10 15:50
 */
public class GitlabUser {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("username")
    private String username;
    @JsonProperty("state")
    private String state;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("bio")
    private String bio;
    @JsonProperty("location")
    private String location;
    @JsonProperty("public_email")
    private String publicEmail;
    @JsonProperty("skype")
    private String skype;
    @JsonProperty("linkedin")
    private String linkedin;
    @JsonProperty("twitter")
    private String twitter;
    @JsonProperty("website_url")
    private String websiteUrl;
    @JsonProperty("organization")
    private String organization;
    @JsonProperty("job_title")
    private String jobTitle;
    @JsonProperty("pronouns")
    private String pronouns;
    @JsonProperty("bot")
    private Boolean bot;
    @JsonProperty("work_information")
    private Object workInformation;
    @JsonProperty("followers")
    private Integer followers;
    @JsonProperty("following")
    private Integer following;
    @JsonProperty("local_time")
    private Object localTime;
    @JsonProperty("last_sign_in_at")
    private String lastSignInAt;
    @JsonProperty("confirmed_at")
    private String confirmedAt;
    @JsonProperty("last_activity_on")
    private String lastActivityOn;
    @JsonProperty("email")
    private String email;
    @JsonProperty("theme_id")
    private Integer themeId;
    @JsonProperty("color_scheme_id")
    private Integer colorSchemeId;
    @JsonProperty("projects_limit")
    private Integer projectsLimit;
    @JsonProperty("current_sign_in_at")
    private String currentSignInAt;
    @JsonProperty("identities")
    private List<Object> identities;
    @JsonProperty("can_create_group")
    private Boolean canCreateGroup;
    @JsonProperty("can_create_project")
    private Boolean canCreateProject;
    @JsonProperty("two_factor_enabled")
    private Boolean twoFactorEnabled;
    @JsonProperty("external")
    private Boolean external;
    @JsonProperty("private_profile")
    private Boolean privateProfile;
    @JsonProperty("commit_email")
    private String commitEmail;
    @JsonProperty("shared_runners_minutes_limit")
    private Object sharedRunnersMinutesLimit;
    @JsonProperty("extra_shared_runners_minutes_limit")
    private Object extraSharedRunnersMinutesLimit;
    @JsonProperty("is_admin")
    private Boolean isAdmin;
    @JsonProperty("note")
    private String note;
    @JsonProperty("using_license_seat")
    private Boolean usingLicenseSeat;
    @JsonProperty("highest_role")
    private Integer highestRole;
    @JsonProperty("current_sign_in_ip")
    private String currentSignInIp;
    @JsonProperty("last_sign_in_ip")
    private String lastSignInIp;
    @JsonProperty("sign_in_count")
    private Integer signInCount;
    @JsonProperty("plan")
    private Object plan;
    @JsonProperty("trial")
    private Boolean trial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPublicEmail() {
        return publicEmail;
    }

    public void setPublicEmail(String publicEmail) {
        this.publicEmail = publicEmail;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPronouns() {
        return pronouns;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    public Boolean getBot() {
        return bot;
    }

    public void setBot(Boolean bot) {
        this.bot = bot;
    }

    public Object getWorkInformation() {
        return workInformation;
    }

    public void setWorkInformation(Object workInformation) {
        this.workInformation = workInformation;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Object getLocalTime() {
        return localTime;
    }

    public void setLocalTime(Object localTime) {
        this.localTime = localTime;
    }

    public String getLastSignInAt() {
        return lastSignInAt;
    }

    public void setLastSignInAt(String lastSignInAt) {
        this.lastSignInAt = lastSignInAt;
    }

    public String getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(String confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public String getLastActivityOn() {
        return lastActivityOn;
    }

    public void setLastActivityOn(String lastActivityOn) {
        this.lastActivityOn = lastActivityOn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public Integer getColorSchemeId() {
        return colorSchemeId;
    }

    public void setColorSchemeId(Integer colorSchemeId) {
        this.colorSchemeId = colorSchemeId;
    }

    public Integer getProjectsLimit() {
        return projectsLimit;
    }

    public void setProjectsLimit(Integer projectsLimit) {
        this.projectsLimit = projectsLimit;
    }

    public String getCurrentSignInAt() {
        return currentSignInAt;
    }

    public void setCurrentSignInAt(String currentSignInAt) {
        this.currentSignInAt = currentSignInAt;
    }

    public List<Object> getIdentities() {
        return identities;
    }

    public void setIdentities(List<Object> identities) {
        this.identities = identities;
    }

    public Boolean getCanCreateGroup() {
        return canCreateGroup;
    }

    public void setCanCreateGroup(Boolean canCreateGroup) {
        this.canCreateGroup = canCreateGroup;
    }

    public Boolean getCanCreateProject() {
        return canCreateProject;
    }

    public void setCanCreateProject(Boolean canCreateProject) {
        this.canCreateProject = canCreateProject;
    }

    public Boolean getTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public Boolean getPrivateProfile() {
        return privateProfile;
    }

    public void setPrivateProfile(Boolean privateProfile) {
        this.privateProfile = privateProfile;
    }

    public String getCommitEmail() {
        return commitEmail;
    }

    public void setCommitEmail(String commitEmail) {
        this.commitEmail = commitEmail;
    }

    public Object getSharedRunnersMinutesLimit() {
        return sharedRunnersMinutesLimit;
    }

    public void setSharedRunnersMinutesLimit(Object sharedRunnersMinutesLimit) {
        this.sharedRunnersMinutesLimit = sharedRunnersMinutesLimit;
    }

    public Object getExtraSharedRunnersMinutesLimit() {
        return extraSharedRunnersMinutesLimit;
    }

    public void setExtraSharedRunnersMinutesLimit(Object extraSharedRunnersMinutesLimit) {
        this.extraSharedRunnersMinutesLimit = extraSharedRunnersMinutesLimit;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getUsingLicenseSeat() {
        return usingLicenseSeat;
    }

    public void setUsingLicenseSeat(Boolean usingLicenseSeat) {
        this.usingLicenseSeat = usingLicenseSeat;
    }

    public Integer getHighestRole() {
        return highestRole;
    }

    public void setHighestRole(Integer highestRole) {
        this.highestRole = highestRole;
    }

    public String getCurrentSignInIp() {
        return currentSignInIp;
    }

    public void setCurrentSignInIp(String currentSignInIp) {
        this.currentSignInIp = currentSignInIp;
    }

    public String getLastSignInIp() {
        return lastSignInIp;
    }

    public void setLastSignInIp(String lastSignInIp) {
        this.lastSignInIp = lastSignInIp;
    }

    public Integer getSignInCount() {
        return signInCount;
    }

    public void setSignInCount(Integer signInCount) {
        this.signInCount = signInCount;
    }

    public Object getPlan() {
        return plan;
    }

    public void setPlan(Object plan) {
        this.plan = plan;
    }

    public Boolean getTrial() {
        return trial;
    }

    public void setTrial(Boolean trial) {
        this.trial = trial;
    }
}
