import '../../static/css/Landing.css'
import React from 'react'
import Transition from 'react-transition-group/Transition';
import {
    Container,
    Row,
    Badge,
} from 'react-bootstrap'
import { 
    Link,
} from 'react-router-dom';

const transitionDuration = 1000

const mainTransitionStyle = {
    transition: `opacity ${transitionDuration}ms ease`,
    opacity: 0
}

const transitionStyles = {
    entering: { opacity: 1 },
    entered: { opacity: 1 },
    exiting: { opacity: 0 },
    exited: { opacity: 0 },
}

class Landing extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            transOne: false,
            transTwo: false
        }
    }

    componentDidMount() {
        setTimeout(() => this.setState({ transOne: true }), 1000)
        setTimeout(() => this.setState({ transTwo: true }), 2000)
    }

    render () {
        return (
            <>
                <Container className="pt-5">
                    <Row className="mt-5">
                        <Transition in={this.state.transOne} timeout={transitionDuration}>
                            {state => (
                                <div style={{
                                    ...mainTransitionStyle,
                                    ...transitionStyles[state]
                                }}>
                                    <h1>Helping you with your ticket needs</h1>
                                </div>
                            )}
                        </Transition>
                    </Row>
                    <Row className="mt-5"><br /></Row>
                    <Row className="mt-5">
                        <Transition in={this.state.transTwo} timeout={transitionDuration}>
                            {state => (
                                <div style={{
                                    ...mainTransitionStyle,
                                    ...transitionStyles[state]
                                }}>
                                    <h2>Start viewing your <Badge variant="primary" as={Link} to="/tickets">Tickets</Badge></h2>
                                </div>
                            )}
                        </Transition>
                    </Row>
                </Container>
            </>
        )
    }
}

export default Landing