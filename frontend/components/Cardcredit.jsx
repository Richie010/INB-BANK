import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { MDBCardText } from 'mdb-react-ui-kit'; // Ensure you have this import if using MDB components

const CreditCard = ({  }) => {
    const [userBalance, setUserBalance] = useState(null); // State to store user balance
    const [error, setError] = useState(null);
    const[userName ,setUserName]=useState(null);

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

    useEffect(() =>{
        const storedAccountId = localStorage.getItem('accountId');
        if (storedAccountId) {
            axios.get(`http://localhost:8082/api/savings/userName/${storedAccountId}`)
                .then(response => {
                    setUserName(response.data);
                }) .catch(error => {
                    setError('There was an error fetching the balance');
                    console.error('There was an error fetching balance', error);
                }
            );
        }else{
            setError('Account ID not found');
        }
    })



   
    return (
        <div className="max-w-xs mx-auto bg-white rounded-lg shadow-md overflow-hidden mt-24">
            <div className="bg-gray-100 px-4 py-2">
                <h2 className="text-lg font-medium text-gray-800">Savings Account</h2>
            </div>
            <div className="px-4 py-5 sm:p-6">
                <div className="flex flex-col items-start justify-between mb-6">
                    <span className="text-sm font-medium text-gray-600">Cardholder Name</span>
                    <span className="text-lg font-medium text-gray-800">  {userName !== null ? (
                            <MDBCardText>
                               {userName}
                            </MDBCardText>
                        ) : (
                            <MDBCardText>
                                Loading Name...
                            </MDBCardText>
                        )}        
                        </span>
                </div>
                <div className="flex flex-col items-start justify-between mb-6">
                    <span className="text-sm font-medium text-gray-600">Card Number</span>
                    <span className="text-lg font-medium text-gray-800">**** **** **** 1234</span>
                </div>
                <div className="flex flex-row items-center justify-between mb-6">
                    <div className="flex flex-col items-start">
                        <span className="text-sm font-medium text-gray-600">Expiration Date</span>
                        <span className="text-lg font-medium text-gray-800">12/24</span>
                    </div>
                    <div className="flex flex-col items-start">
                        <span className="text-sm font-medium text-gray-600">CVV</span>
                        <span className="text-lg font-medium text-gray-800">***</span>
                    </div>
                </div>
                <div className="flex flex-row items-center justify-between">
                    <div className="flex flex-col items-start">
                        <span className="text-sm font-medium text-gray-600">Credit Limit</span>
                        <span className="text-lg font-medium text-gray-800">$10,000</span>
                    </div>
                    <div className="flex flex-col items-start">
                        <span className="text-sm font-medium text-gray-600">Available Balance</span>
                        <span class="text-lg font-medium text-gray-800">
                        {userBalance !== null ? (
                            <MDBCardText>
                               ${userBalance}
                            </MDBCardText>
                        ) : (
                            <MDBCardText>
                                Loading balance...
                            </MDBCardText>
                        )}
                        </span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default CreditCard;
