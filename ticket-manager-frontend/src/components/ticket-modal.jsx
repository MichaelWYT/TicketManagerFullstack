import React from 'react'
import FormHandlerTicket from './form-handler-ticket'
import {
    Modal
} from 'react-bootstrap'

class TicketModal extends React.Component {

    constructor() {
        super()
        this.displayTitle = this.displayTitle.bind(this)
    }

    displayTitle() {
        switch(this.props.mode) {
            case "create":
                return (
                    <Modal.Title>
                        Create a ticket of your issue
                    </Modal.Title>
                )
            case "update":
                return (
                    <Modal.Title>
                        Update the ticket issue
                    </Modal.Title>
                )
            case "delete":
                return (
                    <Modal.Title>
                        Ticket as been deleted
                    </Modal.Title>
                )
            default:
                return (
                    <Modal.Title>
                        Insert 404 not found joke
                    </Modal.Title>
                )
        }
    
    }

    render() {
        return (
            <>
            <Modal show={this.props.onShow} onHide={this.props.onClose}>
                <Modal.Header closeButton>
                    {this.displayTitle()}
                </Modal.Header>
                <Modal.Body>
                    <FormHandlerTicket mode={this.props.mode} ticket={this.props.ticket}/>
                </Modal.Body>
            </Modal>
            </>
        )
    }
}

export default TicketModal