import React, { useEffect, useState } from 'react';
import axios from 'axios';

export function UserDetails() {
    const [userDetail, setUserDetail] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const accountId = localStorage.getItem('accountId');
        if (accountId) {
            axios.get(`http://localhost:8082/api/savings/listofdetails/${accountId}`)
                .then(response => {
                    console.log(response.data);
                    setUserDetail(response.data);
                    // console.log(userDetail);
                })
                .catch(error => {
                    setError('There was an error fetching user details');
                    console.error('There was an error fetching user details', error);
                });
        } else {
            setError('Account ID not found');
        }
    }, []);

    return (
        <div className="container mt-4">
            <h1 className="alert alert-primary text-center">User Profile</h1>
            {error ? (
                <div className="alert alert-danger" role="alert">
                    {error}
                </div>
            ) : (
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>Account ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Dob</th>
                            <th>Phone</th>
                            <th>City</th>
                            <th>State</th>
                            <th>ID Proof</th>
                        </tr>
                    </thead>
                    <tbody>
                        {userDetail ? (
                            // userDetail.map((user,index) => (
                                <tr key={userDetail.accountId}>
                                    {/* <td>{index}</td> */}
                                    <td>{userDetail.accountId}</td>
                                    <td>{userDetail.name}</td>
                                    <td>{userDetail.email}</td>
                                    <td>{userDetail.dob}</td>
                                    <td>{userDetail.phone}</td>
                                    <td>{userDetail.city}</td>
                                    <td>{userDetail.state}</td>
                                    <td>{userDetail.idproof}</td>
                                </tr>
                            // ))
                        ) : (
                            <tr>
                                <td colSpan="8">No user details available</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            )}
            <div>
               {/* <h1>length : {userDetail.length}</h1> */}
            </div>
        </div>
    );
}
