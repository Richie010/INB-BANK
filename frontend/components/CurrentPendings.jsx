import React, { useEffect, useState } from 'react';
import axios from 'axios';

const PendingApprovals = () => {
  const [pendingApprovals, setPendingApprovals] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8082/api/admin/pending')
      .then(response => {
        setPendingApprovals(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the pending approvals!', error);
      });
  }, []);

  return (
    <div className="container bg-white">
      <h1 className="alert bg-white text-center">Pending Approvals</h1>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Account Type</th>
            <th>Phone</th>
            <th>City</th>
            <th>State</th>
            <th>ID Proof</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {pendingApprovals.map((user) => (
            <tr key={user.userId}>
              <td>{user.userId}</td>
              <td>{user.name}</td>
              <td>{user.email}</td>
              <td>{user.accountType}</td>
              <td>{user.phone}</td>
              <td>{user.city}</td>
              <td>{user.state}</td>
              <td>{user.idproof}</td>
              <td>
                <button className="btn btn-success" onClick={() => approveUser(user.userId)}>Approve</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

const approveUser = (userId) => {
  axios.put(`http://localhost:8082/api/admin/approves/${userId}`)
    .then(response => {
      alert('User approved successfully!');
      window.location.reload();
    })
    .catch(error => {
      console.error('There was an error approving the user!', error);
    });
}

export default PendingApprovals;