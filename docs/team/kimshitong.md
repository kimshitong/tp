---
layout: page
title: Shi Tong's Project Portfolio Page
---

### Project: MediConnect

MediConnect - MediConnect is a desktop clinic management application used for managing clinic staff and patients. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 21 kLoC.

Given below are my contributions to the project.


* **New Feature**: 
  * Add Doctor 
    * What it does: Allows user to add doctor to the database
    * Justification: We are able to retrieve multiple inputs to store more details about doctor like name and nric  
    * Highlights: We check the validity of inputs with regular expressions like NRIC will check if it starts with S/T/F/G/M and followed by 7 digits and end with a uppercase alphabet to ensure data integrity

  * Add Doctor's Specialisation
    * What it does: Allows user to add specialisation to an existing doctor in the database 
    * Justification: It allows us to identify the doctor's specialisation like "Orthopaedic" and "Pediatric" quickly  
    * Highlights: We are able to add multiple specialisation to a doctor 

  * Edit Doctor's Remark
    * What it does: Allows user to edit remark of an existing doctor
    * Justification: It allows us to leave a remark of the doctor which can be used to indicate doctor's availability.
    * Highlights: We are able to edit doctor remark whenever there is a new update on availability.
    
  * Other Features
    * View/Edit/Delete/Find/List Doctor
    * Delete Doctor's Specialisation

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=kimshitong&tabRepo=AY2324S1-CS2103T-T08-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false%23%2F)

* **Project management**:
  * Version control 
  * Documentation Management

* **Documentation**:
  * User Guide:
    * Document New Features 
    * Updated Command Summary
    * Updated Parameter Table 
  * Developer Guide:
    * User Stories
    * Document Features for List Patient/Doctor/Appointments with Activity/Sequence UML Diagram
    * Document Features for View Patient/Doctor/Appointments with Activity/Sequence UML Diagram
    * Document Features for Delete Specialisation/Medical Conditions/Prescriptions with Activity/Sequence UML Diagram

* **Community**:
  * PRs reviewed (with non-trivial review comments)
  * Contributed to forum discussions
  * Reported bugs and suggestions for other teams in the class
