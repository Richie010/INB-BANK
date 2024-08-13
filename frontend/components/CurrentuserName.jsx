import React, { useState, useEffect } from 'react';
import axios from 'axios'; // Import axios for making API calls
import { MDBCard, MDBCardBody, MDBCardTitle, MDBCardText } from 'mdb-react-ui-kit';
import { useNavigate } from "react-router-dom";

export function CurrentuserName() {
    const navigate = useNavigate();
    const [userName, setUserName] = useState(null); // State to store user balance
    const [error, setError] = useState(null);

    useEffect(() => {
        const storedAccountId = localStorage.getItem('accountId');
        if (storedAccountId) {
            axios.get(`http://localhost:8082/currentaccount/userName/${storedAccountId}`)
                .then(response => {
                    setUserName(response.data);
                })
                .catch(error => {
                    setError('There was an error fetching the balance');
                    console.error('There was an error fetching balance', error);
                });
        } else {
            setError('Account ID not found');
        }
    }, []);

    return (
        <div className="container-fluid">
            <div className="row">
                <MDBCard className="mt-4">
                    <MDBCardBody>
                        <MDBCardTitle>User Balance</MDBCardTitle>
                        {error && (
                            <MDBCardText className="text-danger">
                                {error}
                            </MDBCardText>
                        )}
                        {userName !== null ? (
                            <MDBCardText>
                                Current Balance: ${userName}
                            </MDBCardText>
                        ) : (
                            <MDBCardText>
                                Loading balance...
                            </MDBCardText>
                        )}
                    </MDBCardBody>
                </MDBCard>
            </div>
        </div>
    );
}
