import React, { useState, useEffect } from 'react';
import { Link, NavLink, Outlet } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios'; // Import axios for making API calls
import { useNavigate } from "react-router-dom";
import { MDBCard, MDBCardBody, MDBCardTitle, MDBCardText } from 'mdb-react-ui-kit';

export function SavingsDashboard() {
    const navigate = useNavigate();
    const [userBalance, setUserBalance] = useState(null); // State to store user balance
    const [error, setError] = useState(null);

    useEffect(() => {
        const storedAccountId = localStorage.getItem('accountId');
        if (storedAccountId) {
            axios.get(`http://localhost:8082/api/savings/userBalance/${storedAccountId}`)
                .then(response => {
                    setUserBalance(response.data);
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
                        {userBalance !== null ? (
                            <MDBCardText>
                                Current Balance: ${userBalance}
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
