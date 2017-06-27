package com.donorschoose;

import java.util.List;

/**
 * Created by Leslie Arizaga on 5/23/2017.
 */

public class Donor {

    private String searchTerms;
    private String searchURL;
    private String totalProposals;
    private String state;
    private List<Proposal> proposals;

    public String getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public String getSearchURL() {
        return searchURL;
    }

    public void setSearchURL(String searchURL) {
        this.searchURL = searchURL;
    }

    public String getTotalProposals() {
        return totalProposals;
    }

    public void setTotalProposals(String totalProposals) {
        this.totalProposals = totalProposals;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    public class Proposal {

        private String title;
        private String shortDescription;
        private String numDonors;
        private String costToComplete;
        private String totalPrice;
        private String teacherName;
        private String schoolName;
        private String proposalURL;
        private  String imageURL;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getNumDonors() {
            return numDonors;
        }

        public void setNumDonors(String numDonors) {
            this.numDonors = numDonors;
        }

        public String getCostToComplete() {
            return costToComplete;
        }

        public void setCostToComplete(String costToComplete) {
            this.costToComplete = costToComplete;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getProposalURL() {
            return proposalURL;
        }

        public void setProposalURL(String proposalURL) {
            this.proposalURL = proposalURL;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }
    }
}


