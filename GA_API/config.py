import os
from typing import Dict, Any

class Config:
    # PostgreSQL Configuration (Render.com)
    DB_URI = os.getenv("DB_URI", "postgresql://evaluation_service:SV9JdLEzslLXkbKcRmEnV1VLHrpZm839@dpg-cvp33afgi27c73auus0g-a.oregon-postgres.render.com/candidate_evaluation_db")
    
    # SQLAlchemy Engine Options (if needed)
    SQLALCHEMY_ENGINE_OPTIONS = {
        'pool_size': 10,
        'pool_recycle': 3600,
        'connect_args': {
            'connect_timeout': 5,
            'sslmode': 'require'  # Render.com requires SSL
        }
    }

# For direct psycopg2 connections
DB_CONFIG: Dict[str, Any] = {
    'dsn': Config.DB_URI,  # Use the full URI
    'connect_timeout': 5
}