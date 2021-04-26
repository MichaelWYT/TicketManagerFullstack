import '../../static/css/Tickets.css'
import React from 'react'
import TicketModal from '../ticket-modal'
import axios from 'axios';
import moment from 'moment'
import {
    Container,
    Row,
    Col,
    Card,
    ButtonGroup,
    Button,
} from 'react-bootstrap'

class Tickets extends React.Component {

    constructor() {
        super();
        this.state = {
            showModal: false,
            modalProps: {},
            tickets: [],
        }
        this.toggleModal = this.toggleModal.bind(this)
        this.displayTickets = this.displayTickets.bind(this)
    }

    componentDidMount() {
        axios.get('/getAllTickets')
        .then((res) => {
            this.setState({tickets: res.data})
        })
        .catch((err) => {
            console.log(err)
        })
    }

    displayTickets(status) {
        if (this.state.tickets.length !== 0) {
            return (
                this.state.tickets.map((ticket, i) => {
                    if (ticket.status === status) {
                        return (
                            <Card key={i} className="my-3">
                                <Card.Body>
                                    <Card.Title><strong>{ticket.title}</strong></Card.Title>
                                    <Card.Text>{ticket.description}</Card.Text>
                                    <ButtonGroup size="sm">
                                        <Button variant="primary" onClick={(e) => this.formType([e.target.value, ticket])} value="update">View me</Button>
                                        <Button variant="danger" onClick={(e) => this.formType([e.target.value, ticket])} value="delete">Delete me</Button>
                                    </ButtonGroup>
                                </Card.Body>
                                <Card.Footer className="text muted">
                                    <small>Days past since creation: {moment().diff(moment(ticket.date), 'days')}</small>
                                </Card.Footer>
                            </Card>
                        )
                    } else {
                        return null
                    }
                })
            )
        }
        return null
    }

    formType(forwardData) {
        this.setState({modalProps: {mode: forwardData[0], ticket: forwardData[1]}})
        this.toggleModal()
    }

    toggleModal() {
        this.setState((state) => ({
            showModal: !state.showModal
        }));
    }

    render() {
        return (
            <Container className="foreground">
                <Container className="content py-5 px-5">
                    <Row className="mb-5">
                        <Button onClick={(e) => this.formType([e.target.value, null])} value="create">Create ticket</Button>
                    </Row>
                    <Row>
                        <Col>
                            <Card><Card.Header as="h4">To do queue</Card.Header></Card>
                            {this.displayTickets("TODO")}
                        </Col>
                        <Col>
                            <Card><Card.Header as="h4">In progress queue</Card.Header></Card>
                            {this.displayTickets("INPROGRESS")}
                        </Col>
                        <Col>
                            <Card><Card.Header as="h4">Done queue</Card.Header></Card>
                            {this.displayTickets("DONE")}
                        </Col>
                    </Row>
                </Container>
                <TicketModal onShow={this.state.showModal} onClose={this.toggleModal} {...this.state.modalProps} />
            </Container>
        )
    }
}

export default Tickets