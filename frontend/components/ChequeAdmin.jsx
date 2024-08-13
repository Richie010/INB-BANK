import React, { useState } from 'react';
import axios from 'axios';

const ProcessBankSlip = () => {
  // State for form input and message
  const [chequeId, setChequeId] = useState('');
  const [bankSlipId, setBankSlipId] = useState('');
  const [message, setMessage] = useState('');

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Construct the URL with the chequeId and bankSlipId
      const url = `http://localhost:8082/bankSlips/process/${chequeId}/${bankSlipId}`;
      
      // Make the POST request to process the bank slip
      const response = await axios.post(url);
      
      // Set the success message
      setMessage(`Bank slip processed successfully with response: ${response.data}`);
      
      // Clear the form fields
      setChequeId('');
      setBankSlipId('');
    } catch (error) {
      // Handle errors
      setMessage('Failed to process bank slip');
    }
  };

  return (
    <div className="container mx-auto px-4 py-8 bg-white">
      <div className="max-w-md mx-auto p-6 rounded-lg shadow-md bg-white">
        <h2 className="text-2xl font-semibold text-black text-center mb-6">Process Bank Slip</h2>
        <form onSubmit={handleSubmit} className='bg-white'>
          <div className="mb-4">
            <label htmlFor="chequeId" className="block text-sm font-medium text-black mb-1">Cheque ID</label>
            <input
              type="number"
              id="chequeId"
              className="block w-full border-gray-300 rounded-md shadow-sm text-black focus:border-black focus:ring-black sm:text-sm"
              value={chequeId}
              onChange={(e) => setChequeId(e.target.value)}
              required
            />
          </div>
          <div className="mb-4">
            <label htmlFor="bankSlipId" className="block text-sm font-medium text-black mb-1">Bank Slip ID</label>
            <input
              type="number"
              id="bankSlipId"
              className="block w-full border-gray-300 rounded-md shadow-sm text-black focus:border-black focus:ring-black sm:text-sm"
              value={bankSlipId}
              onChange={(e) => setBankSlipId(e.target.value)}
              required
            />
          </div>
          <button
            type="submit"
            className="w-full py-2 px-4 bg-black text-white font-semibold rounded-md shadow-sm hover:bg-gray-800 focus:outline-none focus:ring-2 focus:ring-black focus:ring-opacity-50"
          >
            Process
          </button>
        </form>
        {message && <div className="mt-4 text-center text-sm font-medium text-black">{message}</div>}
      </div>
    </div>
  );
};

export default ProcessBankSlip;
