const roleName = {
    Developer: 'Developer',
    Manager: 'Manager'
};

function ProjectDto(id, name, description, author_firstName, author_lastName, author_email) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.author_firstName = author_firstName;
    this.author_lastName = author_lastName;
    this.author_email = author_email;
}

function User(id, firstName, lastName, email, role) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.role = role;
}