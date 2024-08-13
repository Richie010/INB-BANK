// AdminDashboard.jsx
import React from 'react';
import { Link, Outlet } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import PendingApproval from './PendingAproval';
import LockBlocked from './LoginBlocked';
import { useNavigate } from "react-router-dom";

export function AdminDashboard() {
    const navigate = useNavigate();
    return (
       <></>
    );
}
