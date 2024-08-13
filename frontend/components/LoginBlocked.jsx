import React, { useEffect, useState } from 'react';
import axios from 'axios';

const LockBlocked = () => {
  const [pendingApprovals, setPendingApprovals] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8082/api/admin/loginpending')
      .then(response => {
        setPendingApprovals(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the pending approvals!', error);
      });
  }, []);

  return (
    <div className="container mt-4 bg-white">
      <h1 className="alert bg-white  text-center">Pending Approvals</h1>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
           
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {pendingApprovals.map((user) => (
            <tr key={user.accountId}>
              <td>{user.accountId}</td>
              <td>{user.name}</td>
              <td>{user.email}</td>
              <td>{user.accountType}</td>
              <td>
                <button className="btn btn-success" onClick={() => LoginBlocked(user.accountId)}>Approve</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

const LoginBlocked = (accountId) => {
  axios.put(`http://localhost:8082/api/admin/approveCount0/${accountId}`)
    .then(response => {
      alert('Account UnBlocked');
      window.location.reload();
    })
    .catch(error => {
      console.error('There was an error un Blocking user', error);
    });
}

export default LockBlocked;