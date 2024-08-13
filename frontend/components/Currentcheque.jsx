import React, { useState } from 'react';
import axios from 'axios';

const CurrentSlip = () => {
  // State for Cheque Form
  const [currentAccountId, setCurrentAccountId] = useState('');
  const [chequeName, setChequeName] = useState('');
  const [chequeAmount, setChequeAmount] = useState('');
  const [chequeMessage, setChequeMessage] = useState('');

  // State for Bank Slip Form
  const [bankSlipAccountType, setBankSlipAccountType] = useState('current'); // Default to 'current'
  const [bankSlipAccountId, setBankSlipAccountId] = useState('');
  const [bankSlipChequeId, setBankSlipChequeId] = useState('');
  const [bankSlipName, setBankSlipName] = useState('');
  const [bankSlipAmount, setBankSlipAmount] = useState('');
  const [bankSlipMessage, setBankSlipMessage] = useState('');

  // Handle Cheque Form Submission
  const handleChequeSubmit = async (e) => {
    e.preventDefault();

    const chequeData = {
      currentAccountId: parseInt(currentAccountId, 10),
      name: chequeName,
      amount: parseFloat(chequeAmount),
    };

    try {
      const response = await axios.post('http://localhost:8082/cheques/createcheque', chequeData);
      setChequeMessage(`${response.data}`);
      
      // Clear the form fields
      setCurrentAccountId('');
      setChequeName('');
      setChequeAmount('');
    } catch (error) {
      setChequeMessage('Failed to Create Cheque');
    }
  };

  // Handle Bank Slip Form Submission
  const handleBankSlipSubmit = async (e) => {
    e.preventDefault();

    const bankSlipData = {
      [bankSlipAccountType === 'current' ? 'currentAccountId' : 'savingsAccountId']: parseInt(bankSlipAccountId, 10),
      chequeId: parseInt(bankSlipChequeId, 10),
      name: bankSlipName,
      amount: parseFloat(bankSlipAmount),
    };

    try {
      const response = await axios.post('http://localhost:8082/bankSlips/create', bankSlipData);
      setBankSlipMessage(`${response.data}`);
      
      // Clear the form fields
      setBankSlipAccountId('');
      setBankSlipChequeId('');
      setBankSlipName('');
      setBankSlipAmount('');
    } catch (error) {
      setBankSlipMessage('Failed to Create Bank Slip');
    }
  };

  return (
    <div className="container mt-5 bg-white">
      <div className="row justify-content-center">
        <div className="col-md-6">
          {/* Cheque Form */}
          <div className="card mb-4">
            <div className="card-body">
              <h2 className="card-title text-center text-dark font-weight-bold">Create Cheque</h2>
              <form onSubmit={handleChequeSubmit}className='bg-white'>
                <div className="form-group">
                  <label htmlFor="currentAccountId" className="font-weight-bold text-dark">Account ID</label>
                  <input
                    type="number"
                    id="currentAccountId"
                    className="form-control"
                    value={currentAccountId}
                    onChange={(e) => setCurrentAccountId(e.target.value)}
                    required
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="chequeName" className="font-weight-bold text-dark">Name</label>
                  <input
                    type="text"
                    id="chequeName"
                    className="form-control"
                    value={chequeName}
                    onChange={(e) => setChequeName(e.target.value)}
                    required
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="chequeAmount" className="font-weight-bold text-dark">Amount</label>
                  <input
                    type="number"
                    id="chequeAmount"
                    step="0.01"
                    className="form-control"
                    value={chequeAmount}
                    onChange={(e) => setChequeAmount(e.target.value)}
                    required
                  />
                </div>
                <button type="submit" className="btn btn-primary btn-block">Submit Cheque</button>
              </form>
              {chequeMessage && <div className="mt-3 alert alert-info">{chequeMessage}</div>}
            </div>
          </div>

          {/* Bank Slip Form */}
          <div className="card">
            <div className="card-body">
              <h2 className="card-title text-center text-dark font-weight-bold">Create Bank Slip</h2>
              <form onSubmit={handleBankSlipSubmit} className='bg-white'>
                <div className="form-group">
                  <label htmlFor="bankSlipAccountType" className="font-weight-bold text-dark">Account Type</label>
                  <select
                    id="bankSlipAccountType"
                    className="form-control"
                    value={bankSlipAccountType}
                    onChange={(e) => setBankSlipAccountType(e.target.value)}
                    required
                  >
                    <option value="current">Current Account</option>
                    <option value="savings">Savings Account</option>
                  </select>
                </div>
                <div className="form-group">
                  <label htmlFor="bankSlipAccountId" className="font-weight-bold text-dark">Account ID</label>
                  <input
                    type="number"
                    id="bankSlipAccountId"
                    className="form-control"
                    value={bankSlipAccountId}
                    onChange={(e) => setBankSlipAccountId(e.target.value)}
                    required
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="bankSlipChequeId" className="font-weight-bold text-dark">Cheque ID</label>
                  <input
                    type="number"
                    id="bankSlipChequeId"
                    className="form-control"
                    value={bankSlipChequeId}
                    onChange={(e) => setBankSlipChequeId(e.target.value)}
                    required
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="bankSlipName" className="font-weight-bold text-dark">Name</label>
                  <input
                    type="text"
                    id="bankSlipName"
                    className="form-control"
                    value={bankSlipName}
                    onChange={(e) => setBankSlipName(e.target.value)}
                    required
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="bankSlipAmount" className="font-weight-bold text-dark">Amount</label>
                  <input
                    type="number"
                    id="bankSlipAmount"
                    step="0.01"
                    className="form-control"
                    value={bankSlipAmount}
                    onChange={(e) => setBankSlipAmount(e.target.value)}
                    required
                  />
                </div>
                <button type="submit" className="btn btn-primary btn-block">Submit Bank Slip</button>
              </form>
              {bankSlipMessage && <div className="mt-3 alert alert-info">{bankSlipMessage}</div>}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CurrentSlip;
