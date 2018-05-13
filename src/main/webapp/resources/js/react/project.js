function updateState() {
    this.setState({active : true})
}

class ProjectHeader extends React.Component {

    constructor(props) {
        super(props);
    }

    updateChild() {
        updateState()
    }

    render () {

        let createProjectButton;
        if (user.role == roleName.Manager) {
            createProjectButton = (
                <button id="create-new-project" onClick={this.updateChild} className="btn btn-default">
                    <i className="fa fa-plus"></i> <span>Добавить новый</span>
                </button>
            )
        }

        return (
            <div>
                <h1>Мои проекты
                    {createProjectButton}
                    <hr/>
                </h1>

                <CreateProject/>
            </div>);
    }
}

class CreateProject extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            active: false
        };

        updateState = updateState.bind(this);
        this.close = this.close.bind(this);
        this.submit = this.submit.bind(this);
    }

    submit () {
        var name = this.refs.name.value;
        var description = this.refs.description.value;

        axios.put(
            '/api/project/', {
            name : name,
            description : description
        })
            .then(function (response) {
                if (response.data != null) {
                    var id = response.data;
                    addProject(id, name, description);
                }
            })
            .catch(function (error) {
                console.log(error)
            });

        this.setState({active : false})
    }

    close () {
        this.setState({active : false})
    }

    render () {

        if (this.state.active) {
            return (
                <div>
                    <div className="form-group">
                        <label className="control-label">Название проекта</label>
                        <input type="text" ref="name" className="form-control" placeholder="Название проекта"/>
                    </div>

                    <div className="form-group">
                        <label className="control-label">Описание проекта</label>
                        <textarea ref="description" className="form-control" placeholder="Описание проекта"/>
                    </div>

                    <button onClick={this.submit} className="btn btn-primary btn-icon-custom"><i className="fa fa-check"></i></button>
                    <button onClick={this.close} className="btn btn-default btn-icon-custom"><i className="fa fa-times"></i></button>

                    <hr />
                </div>
            );

        }

        return (<div></div>);

    }
}

class Project extends React.Component {
    render () {
        var project = this.props.children;
        return (
            <div>
                <a href={'/project/' + project.id} className="caption">{project.name}</a>
                <p className="body">{project.description}</p>
                <p><b>Менеджер :</b> {project.author_firstName + ' ' + project.author_lastName + ' (' + project.author_email + ')'}</p>
                <hr/>
            </div>
        );
    }

}

function addProject(id, name, description) {
    var project = new ProjectDto(id, name, description, user.firstName, user.lastName, user.email);

    var arr = this.state.projects;
    arr.unshift(project);

    this.setState({projects : arr})
}

class Projects extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            projects: this.props.children
        };

        addProject = addProject.bind(this);
    }

    render () {
        return (
            <div>
                <ProjectHeader />
                {
                    this.state.projects.map (function (item, i) {
                        return (<Project>{item}</Project>);
                    })
                }
            </div>
        );
    }


}

function getProjects() {
    axios.get('/api/project/')
        .then(function (response) {

            var data = response.data;

            if (data.length > 0) {
                ReactDOM.render(
                    <Projects>{response.data}</Projects>,
                    document.getElementById('root')
                );

            } else {
                ReactDOM.render(
                    <div>
                        <ProjectHeader />
                        <h2>Список проектов пуст</h2>
                        <hr/>
                    </div>,
                    document.getElementById('root')
                );
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

getProjects();