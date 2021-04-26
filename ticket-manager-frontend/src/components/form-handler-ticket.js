import React, { useState, useEffect } from 'react'
import axios from 'axios'
import { 
    Form,
    Button,
} from 'react-bootstrap'

const statuses = [
    {
        name: 'To do',
        value: 'TODO'   
    },
    {
        name: 'In progress', 
        value: 'INPROGRESS'
    },
    {
        name: 'Done',
        value: 'DONE'
    }
]

const FormHandlerTicket = (props) => {
    const errors = {}

    const [updateType, setUpdateType] = useState()
    const [ticketData, setTicketData] = useState()
    const [isValid, setValidated] = useState(props.mode === "update" ? true : false)

    const validateForm = () => {
        if (ticketData['title']) {
            if (ticketData['title'].trim() === "") {
                errors['title'] = "Please enter a title of the issue."
                setValidated(false)
            }
        }

        if (ticketData['author']) {
            if (ticketData['author'].trim() === "") {
                errors['author'] = "Please enter your name on the ticket."
                setValidated(false)
            }
        }

        if (Object.keys(errors).length === 0) {
            setValidated(true)
        }
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        let formatTicketData = {}
        let formData = new FormData(e.target)
        formData.forEach((v, k) => {
            formatTicketData[k] = v
        })
        if (props.mode === "update") {
            let changedCounter = 0;
            let fieldsChanged = {}
            for (let ticketKey of Object.keys(formatTicketData)) {
                if (formatTicketData[ticketKey] !== props.ticket[ticketKey]) {
                    fieldsChanged[ticketKey] = formatTicketData[ticketKey]
                    changedCounter++;
                }
            }
            let keyLength = Object.keys(formatTicketData).length
            if (changedCounter < keyLength) {
                setUpdateType("patch")
                setTicketData(fieldsChanged)
            } else {
                setUpdateType("put")
                setTicketData(formatTicketData)
            }
        } else {
            setTicketData(formatTicketData)
        }
    }

    useEffect(() => {
        if(ticketData && props.mode !== "delete") {
            validateForm()
        }
    }, [ticketData])

    useEffect(() => {
        if (props.mode === "create") {
            if (isValid) {
                axios.post('/createTicket', {
                    data: ticketData
                }).then((res) => {
                    console.log(res)
                }).catch((err) => {
                    console.log(err)
                })
            }
        } else if (props.mode === "update" && (updateType !== undefined)) {
            if (isValid) {
                if (updateType === "patch") {
                    axios.patch(`/updatePartial/${props.ticket.id}`, {
                        data: ticketData
                    }).then((res) => {
                        console.log(res)
                    }).catch((err) => {
                        console.log(err)
                    })
                } else if (updateType === "put") {
                    axios.put(`/updateAll/${props.ticket.id}`, {
                        data: ticketData
                    }).then((res) => {
                        console.log(res)
                    }).catch((err) => {
                        console.log(err)
                    })
                }
            }
        } else if (props.mode === "delete") {
            axios.delete(`/deleteTicket/${props.ticket.id}`)
            .then((res) => {
                console.log(res)
            })
            .catch((err) =>{
                console.log(err)
            })
        }
    }, [isValid, updateType])

    return (
        <> 
            {
                props.mode === "delete" ?

                <h5>Ticket delete successful</h5>

                :

                <Form validated={isValid} onSubmit={onFormSubmit} autoComplete="off">
                    <Form.Group controlId="formGroupTitle">
                        <Form.Label>Title</Form.Label>
                        <Form.Control name="title" isInvalid={!isValid} onChange={(e) => e.target.value} defaultValue={props.mode === "update" ? props.ticket.title : ""} required/>
                        <Form.Control.Feedback type="invalid">{errors.title}</Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group controlId="formGroupReporter">
                        <Form.Label>Reporter</Form.Label>
                        <Form.Control name="author" isInvalid={!isValid} onChange={(e) => e.target.value} defaultValue={props.mode === "update" ? props.ticket.author : ""} required/>
                        <Form.Control.Feedback type="invalid">{errors.reporter}</Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group controlId="formGroupDescription">
                        <Form.Label>Description</Form.Label>
                        <Form.Control name="description" as="textarea" onChange={(e) => e.target.value} defaultValue={props.mode === "update" ? props.ticket.description : ""}/>
                    </Form.Group>
                    <Form.Group controlId="formGroupStatus">
                        <Form.Label>Ticket Status</Form.Label>
                        <Form.Control name="status" as="select" defaultValue={props.mode === "update" ? props.ticket.status : statuses[0].name}>
                            {statuses.map((status, i) => {
                                return <option key={i} value={status.value}>{status.name}</option>
                            })}
                        </Form.Control>
                    </Form.Group>
                    {
                        props.mode === "update" ? 
                        <Form.Group>
                            <Form.Label>
                                Created at: {props.ticket.date}
                            </Form.Label>
                        </Form.Group>
                        : null
                    }
                    <Form.Group controlId="formGroupButtons">
                        <Button className="mr-3" variant="primary" type="submit">{props.mode === "update" ? "Update ticket" : "Submit ticket"}</Button>
                        {props.mode === "create" ? <Button className="mx-3" variant="danger" type="reset">Clear</Button> : null}
                        {props.mode !== "create" ? <Button className="mr-3" variant="danger" type="submit">Delete</Button> : null}
                    </Form.Group>
                </Form>
            }
        </>
    )
}

export default FormHandlerTicket