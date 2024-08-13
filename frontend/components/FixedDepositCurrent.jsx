import { useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";

export function FixedDepositCurrent() {
    let [accountId, setAccountId] = useState("");
    let [depositAmount, setDepositAmount] = useState("");
    let [duration, setDuration] = useState("");
    let [interestRate, setInterestRate] = useState("");
    let [success, setSuccess] = useState(false);
    let [message, setMessage] = useState("");

    let handleSubmit = (event) => {
        event.preventDefault();
        let store_url = 'http://localhost:8082/api/FD/create';
        axios.post(store_url, { "accountId": accountId, "depositAmount": depositAmount, "duration": duration, "interestRate": interestRate })
            .then(s => {
                console.log(s);
                setMessage(`Fixed Deposit Created :: Interest Rate: ${s.data.interestRate}`);
                setSuccess(true);
                clearForm();
            }).catch(e => {
                console.log(e);
                setMessage("There was an error with your submission.");
                setSuccess(false);
            });
    };

    let clearForm = () => {
        setAccountId("");
        setDepositAmount("");
        setDuration("");
        setInterestRate("");
    };

    return (
        <div className="flex min-h-full flex-1 flex-col justify-center py-6 sm:px-6 lg:px-8">
            <div className="sm:mx-auto sm:w-full sm:max-w-md">
                <h2 className="mt-6 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                    Fixed Deposit
                </h2>
                {message && (
                        <div className={`alert ${success ? 'alert-success' : 'alert-danger'} text-center`}>
                            {message}
                        </div>
                    )}
            </div>

            <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-[480px]">
                <div className="bg-white px-6 py-12 shadow sm:rounded-lg sm:px-12">
                    <form onSubmit={handleSubmit} className="space-y-6">
                        <div>
                            <label htmlFor="AccountID" className="block text-sm font-medium leading-6 text-gray-900">
                                Account ID
                            </label>
                            <div className="mt-2">
                                <input
                                    id="AccountID"
                                    name="AccountID"
                                    type="text"
                                    value={accountId}
                                    onChange={(event) => setAccountId(event.target.value)}
                                    required
                                    autoComplete="AccountID"
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                />
                            </div>
                        </div>

                        <div>
                            <label htmlFor="duration" className="block text-sm font-medium leading-6 text-gray-900">
                                Duration (months)
                            </label>
                            <div className="mt-2">
                                <select
                                    id="duration"
                                    name="duration"
                                    value={duration}
                                    onChange={(event) => setDuration(event.target.value)}
                                    required
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                >
                                    <option value="">Select duration</option>
                                    <option value="12">12 Months</option>
                                    <option value="24">24 Months</option>
                                    <option value="36">36 Months</option>
                                </select>
                            </div>
                        </div>

                        <div>
                            <label htmlFor="interestRate" className="block text-sm font-medium leading-6 text-gray-900">
                                Interest Rate
                            </label>
                            <div className="mt-2">
                                <select
                                    id="interestRate"
                                    name="interestRate"
                                    value={interestRate}
                                    onChange={(event) => setInterestRate(event.target.value)}
                                    required
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                >
                                    <option value="">Select interest rate</option>
                                    <option value="4">4%</option>
                                    <option value="8">8%</option>
                                    <option value="10">10%</option>
                                </select>
                            </div>
                        </div>

                        <div>
                            <label htmlFor="depositAmount" className="block text-sm font-medium leading-6 text-gray-900">
                                Amount to Deposit
                            </label>
                            <div className="mt-2">
                                <input
                                    id="depositAmount"
                                    name="depositAmount"
                                    type="text"
                                    required
                                    value={depositAmount}
                                    onChange={(event) => setDepositAmount(event.target.value)}
                                    autoComplete="depositAmount"
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                />
                            </div>
                        </div>

                        <div>
                            <button
                                type="submit"
                                className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                            >
                                Submit
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}
