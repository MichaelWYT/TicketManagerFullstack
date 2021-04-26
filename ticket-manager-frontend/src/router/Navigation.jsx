import React from 'react'
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import { 
    Link
} from 'react-router-dom';
import routes from '../router/router-config'

class Navigation extends React.Component {
    render() {
        return (
            <Navbar sticky="top" bg="light" variant="light" expand="sm">
                <Navbar.Brand>Ticket Manager</Navbar.Brand>
                    <Nav variant="tabs" defaultActiveKey="/">
                        {routes.map((route, i) => {
                            return (
                                <Nav.Item key={i}>
                                    <Nav.Link key={i} as={Link} to={route.path}>{route.name}</Nav.Link>
                                </Nav.Item>
                            )
                        })}
                    </Nav>
            </Navbar>
        )
    }
}

export default Navigation