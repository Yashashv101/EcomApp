import React, { useContext } from "react";
import { Navigate } from "react-router-dom";
import AuthContext from "../Context/AuthContext";

const ProtectedRoute = ({ children, requiredRole }) => {
    const { isAuthenticated, user } = useContext(AuthContext);

    if (!isAuthenticated) {
        return <Navigate to="/login" replace />;
    }

    if (requiredRole) {
        const allowedRoles = Array.isArray(requiredRole)
            ? requiredRole
            : [requiredRole];
        if (!allowedRoles.includes(user.role)) {
            return (
                <div className="container mt-5 pt-5 text-center">
                    <h3 className="text-danger">Access Denied</h3>
                    <p>You don't have permission to access this page.</p>
                    <a href="/" className="btn btn-primary">
                        Go Home
                    </a>
                </div>
            );
        }
    }

    return children;
};

export default ProtectedRoute;
