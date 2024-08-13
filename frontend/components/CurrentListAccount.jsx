import React, { useState } from 'react';
import axios from 'axios';

const CurrentListAccounts = () => { const [userId, setUserId] = useState(''); // To store the user ID input
    const [accounts, setAccounts] = useState([]); // To store the fetched account details
    const [error, setError] = useState(''); // To store any error messages
    const [accountType, setAccountType] = useState('current'); // To store the selected account type

    // Function to handle user ID input change
    const handleInputChange = (e) => {
        setUserId(e.target.value);
    };

    // Function to handle account type change
    const handleAccountTypeChange = (e) => {
        setAccountType(e.target.value);
    };

    // Function to fetch account details based on selected account type
    const fetchAccountDetails = () => {
        if (userId.trim()) { // Check if user ID is not empty
            const endpoint = accountType === 'current'
                ? `http://localhost:8082/currentaccount/listacc/${userId}`
                : `http://localhost:8082/api/savings/listacc/${userId}`;

            axios.get(endpoint)
                .then(response => {
                    if (response.data.length === 0) {
                        setError('No account details found for this user ID.');
                        setAccounts([]);
                    } else {
                        setAccounts(response.data);
                        setError('');
                    }
                })
                .catch(error => {
                    console.error('There was an error fetching the account details', error);
                    setError('There was an error fetching the account details.');
                    setAccounts([]);
                });
        }
    };

    return (
        <div className="overflow-hidden rounded-md border border-gray-300 bg-white p-4">
            <h1 className="text-xl font-semibold mb-4">Account Details</h1>
            
            <div className="mb-4">
                <input
                    type="number"
                    placeholder="Enter user ID"
                    value={userId}
                    onChange={handleInputChange}
                    min="1"
                    className="border border-gray-300 p-2 rounded-md"
                />
                <select
                    value={accountType}
                    onChange={handleAccountTypeChange}
                    className="ml-2 border border-gray-300 p-2 rounded-md"
                >
                    <option value="current">Current Account</option>
                    <option value="savings">Savings Account</option>
                </select>
                <button
                    onClick={fetchAccountDetails}
                    className="ml-2 px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600"
                >
                    Fetch Details
                </button>
            </div>

            {error && <p style={{ color: 'red' }}>{error}</p>}
            
            {accounts.length > 0 ? (
                <ul role="list" className="divide-y divide-gray-300">
                    {accounts.map((account) => (
                        <li key={account.accountId} className="px-6 py-4">
                            Account ID: {account.accountId} - Balance: ${account.balance.toFixed(2)} - Name: {account.name}
                        </li>
                    ))}
                </ul>
            ) : (
                userId && <p>No account details found.</p>
            )}
        </div>
    );
};


export default CurrentListAccounts;
