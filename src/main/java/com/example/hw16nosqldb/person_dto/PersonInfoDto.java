package com.example.hw16nosqldb.person_dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PersonInfoDto {

    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private boolean married;

    public PersonInfoDto() {
    }


    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean isMarried() {
        return this.married;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMarried(boolean isMarried) {
        this.married = isMarried;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PersonInfoDto)) return false;
        final PersonInfoDto other = (PersonInfoDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        if (this.getAge() != other.getAge()) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        if (this.isMarried() != other.isMarried()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PersonInfoDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        result = result * PRIME + this.getAge();
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        result = result * PRIME + (this.isMarried() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "PersonInfoDto(id=" + this.getId() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", age=" + this.getAge() + ", email=" + this.getEmail() + ", isMarried=" + this.isMarried() + ")";
    }
}
