import React, { useEffect, useState } from 'react';

const FinancialRecords = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Function to fetch data
  const fetchData = async () => {
    try {
      setLoading(true);
      const response = await fetch('http://localhost:8082/api/admin/pendings');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const result = await response.json();
      setData(result);
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  // Define headers for the table
  const headers = ['Savings Account ID', 'Current Account ID', 'Submission Date', 'Name', 'Bank Slip ID', 'Cheque ID', 'Amount'];

  return (
    <div>
      <h1>Bank Slips Spending</h1>
      <div className="mt-8 flow-root overflow-hidden">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <table className="w-full text-left">
            <thead className="bg-white">
              <tr>
                <th scope="col" className="relative isolate py-3.5 pr-3 text-left text-sm font-semibold text-gray-900">
                  Name
                  <div className="absolute inset-y-0 right-full -z-10 w-screen border-b border-b-gray-200" />
                  <div className="absolute inset-y-0 left-0 -z-10 w-screen border-b border-b-gray-200" />
                </th>
                <th
                  scope="col"
                  className="hidden px-3 py-3.5 text-left text-sm font-semibold text-gray-900 sm:table-cell"
                >
                  Cheque Id
                </th>
                <th
                  scope="col"
                  className="hidden px-3 py-3.5 text-left text-sm font-semibold text-gray-900 sm:table-cell"
                >
                  BankSlip Id
                </th>
                <th
                  scope="col"
                  className="hidden px-3 py-3.5 text-left text-sm font-semibold text-gray-900 md:table-cell"
                >
                  account Id
                </th>
                <th
                  scope="col"
                  className="hidden px-3 py-3.5 text-left text-sm font-semibold text-gray-900 md:table-cell"
                >
                  ammount 
                </th>
                <th
                  scope="col"
                  className="hidden px-3 py-3.5 text-left text-sm font-semibold text-gray-900 md:table-cell"
                >
                  Submission date
                </th>
                <th scope="col" className="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">
                 
                </th>
                <th scope="col" className="relative py-3.5 pl-3">
                
                </th>
              </tr>
            </thead>
            <tbody>
              {data.map((person, index) => (
                <tr key={index}>
                  <td className="relative py-4 pr-3 text-sm font-medium text-gray-900">
                    {person.name}
                    <div className="absolute bottom-0 right-full h-px w-screen bg-gray-100" />
                    <div className="absolute bottom-0 left-0 h-px w-screen bg-gray-100" />
                  </td>
                  <td className="hidden px-3 py-4 text-sm text-gray-500 sm:table-cell">{person.chequeId}</td>
                  <td className="hidden px-3 py-4 text-sm text-gray-500 md:table-cell">{person.bankSlipId}</td>
                  <td className="px-3 py-4 text-sm text-gray-500">{person.savingsAccountId}</td>
                  <td className="px-3 py-4 text-sm text-gray-500">{person.amount}</td>
                  <td className="px-3 py-4 text-sm text-gray-500">{person.submissionDate}</td>
                 
                  <td className="relative py-4 pl-3 text-right text-sm font-medium">
                    
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      <table>
        {/* <thead>
          <tr>
            {headers.map((header, index) => (
              <th key={index}>{header}</th>
            ))}
          </tr>
        </thead> */}
        {/* <tbody>
          {data.length > 0 ? (
            data.map((item, rowIndex) => (
              <tr key={rowIndex}>
                <td>{item.savingsAccountId}</td>
                <td>{item.currentAccountId}</td>
                <td>{item.submissionDate}</td>
                <td>{item.name}</td>
                <td>{item.bankSlipId}</td>
                <td>{item.chequeId}</td>
                <td>{item.amount}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={headers.length}>No data available</td>
            </tr>
          )}
        </tbody> */}
      </table>
    </div>
  );
};

export default FinancialRecords;
