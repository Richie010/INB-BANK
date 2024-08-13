import React from 'react';
import { Routes, Route } from 'react-router-dom';
import {
  MDBContainer,
  MDBNavbar,
  MDBNavbarNav,
  MDBNavbarItem,
  MDBNavbarLink,
  MDBCard,
  MDBCardBody,
  MDBRow
} from 'mdb-react-ui-kit';

import './App.css'; // Import custom CSS

import { Register } from './components/Registration';
import { AdminRegister } from './components/Admin';
import { AuthProvider } from './components/AuthContext';
import PrivateRoute from './components/PrivateRoute';
import { AdminDashboard } from './components/AdminDashboard';
import PendingApproval from './components/PendingAproval';
import { SavingLogin } from './components/Login';
import LoginPage from './components/LoginPage';
import { SavingsDashboard } from './components/SavingsDashboard';
import { DepositSaving } from './components/DepositSaving';
import { WithdrawSaving } from './components/WithdrawSaving';
import { FixedDepositSaving } from './components/FixedDepositSaving';
import LockBlocked from './components/LoginBlocked';
import { Savingstransfer } from './components/Savingstransfer';
import { UserDetails } from './components/userDetails';
import CheckDepositApproval from './components/CheckDepositApproval';
import Example from './components/Demo';
import Admindash from './components/AdminModule';
import RegisterModule from './components/RegisterModule';
import CreditCard from './components/Cardcredit';
import UserProfile from './components/UserModule';
import { CurrentLogin } from './components/CurrentLogin';
import DarkHome from './components/DarkPage';
import CurrentDashboard from './components/CurrentDashboard';
import { DepositCurrent } from './components/CurrentDeposit';
import { WithdarwCurrent } from './components/CurrentWithdraw';
import { EnableOverdraft } from './components/EnableOverdraft';
import { BillPaySaving } from './components/BillPaymentsSaving';
import PaySaving from './components/Products';
import ListOfAccounts from './components/ListOfaccounts';
import RegisterForm from './components/ExistRegister';
import { CurrentTransfer } from './components/CurrentTransfer';
import { BillPayCurrent } from './components/BillpayCurrent';
import BankSlipForm from './components/cheque';
import ProcessBankSlip from './components/ChequeAdmin';
import FinancialRecords from './components/ChequeList';
import PendingApprovals from './components/CurrentPendings';
import CurrentSlip from './components/Currentcheque';
import { FixedDepositCurrent } from './components/FixedDepositCurrent';
import { CurrentBalance } from './components/CurrentuserBalance';
import CurrentCreditCard from './components/Currentcredit';
import { CurrentUserDetails } from './components/CurrentUserdetails';
import CurrentListAccounts from './components/CurrentListAccount';
import DummyApp from './components/DummyLogin';
import Dashboard from './components/CommanDashboard';
import { SavingsBalance } from './components/userBalance';
import CurrentTransactionDetails from './components/CurrentTransactionList';
import SavingsTransactionDetails from './components/SavingsTransactionlist';

function App() {
  return (
    <AuthProvider>
      <Routes>
        {/* Public Routes */}
        <Route path="/" element={<DarkHome />}>
          <Route path="/" element={<Dashboard />} />
          <Route path="/RegisterModule" element={<RegisterModule />} />
          <Route path="/Ulogin" element={<LoginPage />} />
          <Route path="/CurLogin" element={<CurrentLogin />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<AdminRegister />} />
          <Route path="/existreg" element={<RegisterForm />} />
          <Route path="/dummy" element={<DummyApp />} />
        </Route>

        {/* Admin Routes */}
      
        <Route path="/" element={<Admindash />}>
          <Route path="/AdminDashboard" element={<AdminDashboard />} />
          <Route path="/PendingAproval" element={<PendingApproval />} />
          <Route path="/currentPending" element={<PendingApprovals />} />
          <Route path="/LockBlocked" element={<LockBlocked />} />
          <Route path="/ChequeApprove" element={<ProcessBankSlip />} />
          <Route path="/chequelist" element={<FinancialRecords />} />
        </Route>

        {/* Saving Routes */}
        
        <Route path="/" element={<Example />}>
          <Route path="/Dashboard" element={<SavingsBalance />} />
          <Route path="/UserProfile" element={<UserProfile />} />
          <Route path="/DepositSaving" element={<DepositSaving />} />
          <Route path="/WithdrawSaving" element={<WithdrawSaving />} />
          <Route path="/FixedDepositSaving" element={<FixedDepositSaving />} />
          <Route path="/Savingstransfer" element={<Savingstransfer />} />
          <Route path="/CreditCard" element={<CreditCard />} />
          <Route path="/Billpay" element={<BillPaySaving />} />
          <Route path="/cheque" element={<BankSlipForm />} />
          <Route path="/List-Of-Accounts" element={<ListOfAccounts />} />
          <Route path='/SaveTrans' element={<SavingsTransactionDetails />} />
        </Route>

        {/* Current Routes */}
        <Route path="/" element={<CurrentDashboard />}>
          <Route path="/balance" element={<CurrentBalance />} />
          <Route path="/userdetails" element={<CurrentUserDetails />} />
          <Route path="/WithdrawCurrent" element={<WithdarwCurrent />} />
          <Route path="/enbaleOverdraft" element={<EnableOverdraft />} />
          <Route path="/DepsoitCurrent" element={<DepositCurrent />} />
          <Route path="/currentransfer" element={<CurrentTransfer />} />
          <Route path="/CurrentCredit" element={<CurrentCreditCard />} />
          <Route path="/billpays" element={<BillPayCurrent />} />
          <Route path="/Currentcheque" element={<CurrentSlip />} />
          <Route path="/Curretfixed" element={<FixedDepositCurrent />} />
          <Route path="/Listofacc" element={<CurrentListAccounts />} />
          <Route path='/translist' element={<CurrentTransactionDetails />} />
        </Route>

        {/* Demo Route
        // <Route path="/demoo" element={<Example />} /> */}
      </Routes>
     </AuthProvider>
  );
}

export default App;
