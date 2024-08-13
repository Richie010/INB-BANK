import React, { useState, useEffect } from 'react';
import axios from 'axios';

const SavingsTransactionDetails = () => {
  const [transactions, setTransactions] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchTransactionDetails = async () => {
      // Retrieve accountId from localStorage
      const storedAccountId = localStorage.getItem('accountId');
      if (!storedAccountId) {
        setError('Account ID not found in localStorage');
        setLoading(false);
        return;
      }

      try {
        const response = await axios.get(`http://localhost:8082/api/savings/viewtrans/${storedAccountId}`);
        console.log(response.data); // Note: Check the structure of the data here
        setTransactions(response.data);
      } catch (err) {
        setError('Failed to fetch transaction details');
      } finally {
        setLoading(false);
      }
    };

    fetchTransactionDetails();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;
  if (transactions.length === 0) return <p>No transaction details found</p>;

  return (
    <div>
      <h1>Transaction History</h1>
      <div className="mt-8 flow-root overflow-hidden">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <table className="w-full text-left">
            <thead className="bg-white">
              <tr>
                <th scope="col" className="relative isolate py-3.5 pr-3 text-left text-sm font-semibold text-gray-900">
                  Account Id
                  <div className="absolute inset-y-0 right-full -z-10 w-screen border-b border-b-gray-200" />
                  <div className="absolute inset-y-0 left-0 -z-10 w-screen border-b border-b-gray-200" />
                </th>
                <th scope="col" className="hidden px-3 py-3.5 text-left text-sm font-semibold text-gray-900 sm:table-cell">
                  Transaction  Id
                </th>
                <th scope="col" className="hidden px-3 py-3.5 text-left text-sm font-semibold text-gray-900 sm:table-cell">
                  Description
                </th>
                <th scope="col" className="hidden px-3 py-3.5 text-left text-sm font-semibold text-gray-900 md:table-cell">
                  Account Type
                </th>
                <th scope="col" className="hidden px-3 py-3.5 text-left text-sm font-semibold text-gray-900 md:table-cell">
                  Amount
                </th>
                <th scope="col" className="hidden px-3 py-3.5 text-left text-sm font-semibold text-gray-900 md:table-cell">
                  Transaction Date
                </th>
                <th scope="col" className="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">
                  Target Account
                </th>
                
              </tr>
            </thead>
            <tbody>
              {transactions.map((transaction) => (
                <tr key={transaction.id}>
                  <td className="relative py-4 pr-3 text-sm font-medium text-gray-900">
                    {transaction.accountId || 'N/A'} {/* Assuming you have a name field; otherwise, replace with appropriate field */}
                    <div className="absolute bottom-0 right-full h-px w-screen bg-gray-100" />
                    <div className="absolute bottom-0 left-0 h-px w-screen bg-gray-100" />
                  </td>
                  <td className="hidden px-3 py-4 text-sm text-gray-500 sm:table-cell">
                    {transaction.id || 'N/A'}
                  </td>
                  <td className="hidden px-3 py-4 text-sm text-gray-500 sm:table-cell">
                    {transaction.description || 'N/A'}
                  </td>
                  <td className="px-3 py-4 text-sm text-gray-500">
                    {transaction.accounType || 'N/A'}
                  </td>
                  <td className="px-3 py-4 text-sm text-gray-500">
                  ₹ {transaction.amount || '0.00'}
                  </td>
                  <td className="px-3 py-4 text-sm text-gray-500">
                    {transaction.transactionDate || 'N/A'}
                  </td>
                  <td className="px-3 py-4 text-sm text-gray-500">
                    {transaction.targetAccount || 'N/A'}
                  </td>
                  <td className="relative py-4 pl-3 text-right text-sm font-medium">
                   
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default SavingsTransactionDetails;
